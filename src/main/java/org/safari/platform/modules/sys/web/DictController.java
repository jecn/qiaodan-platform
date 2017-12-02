package org.safari.platform.modules.sys.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.safari.platform.common.config.Global;
import org.safari.platform.common.utils.PageUtil;
import org.safari.platform.common.utils.StringUtil;
import org.safari.platform.common.web.BaseController;
import org.safari.platform.modules.sys.entity.Dict;
import org.safari.platform.modules.sys.service.DictService;
import org.safari.platform.modules.sys.service.PrivilegeService;
import org.safari.platform.modules.sys.utils.SysUtil;
import org.safari.platform.modules.sys.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 字典controller
 * @author Alitalk
 * @date 2017-02-17
 */
@Controller
@RequestMapping("${adminPath}/sys/dict")
public class DictController extends BaseController{
	
	@Autowired
	private DictService mdictService;
	
	@Autowired
	private PrivilegeService mprivilegeService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "sys/dictList";
	}
	
	/**
	 * 查看字典信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") String id, Model model) {
		model.addAttribute("dict", mdictService.get(id));
		return "sys/dictView";
	}

	/**
	 * 获取字典json
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="list",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request,Dict dict) {
		dict.page(request);
		PageUtil<Dict> page = new PageUtil<Dict>();
		
		mdictService.findPage(page,dict);
		return resultData(page);
	}
	
	/**
	 * 添加字典跳转
	 * @param model
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("modules", SysUtil.getModules());
		model.addAttribute("dict", new Dict());
		model.addAttribute("isAdmin","");
		return "sys/dictForm";
	}
	
	/**
	 * 修改字典跳转
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id, Model model) {
		model.addAttribute("modules", SysUtil.getModules());
		model.addAttribute("dict", mdictService.get(id));
		model.addAttribute("isAdmin",UserUtil.getUser().isAdmin()? "1":"0");
		return "sys/dictForm";
	}

	/**
	 * 保存字典信息
	 * 
	 * @param dict
	 * @param model
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public String save(@Valid @ModelAttribute("dict")Dict dict, Model model) {
		try {
			if("false".equals(checkValue(dict.getId(), dict.getType(), dict.getValue()))){
				return "fail";
			}
			mdictService.save(dict);
		} catch (Exception e) {
			LOG.error("字典保存失败",e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	/**
	 * 删除字典
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") String id) {
		try {
			
			Dict dict = new Dict(id);
			dict.setDelFlag(Global.DEL_YES);
			mdictService.delete(dict);
		} catch (Exception e) {
			LOG.error("字典删除失败",e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	/**
	 * Ajax请求校验value是否唯一。
	 */
	@RequestMapping(value = "checkValue")
	@ResponseBody
	public String checkValue(String id,String type,String value) {
		Dict dict = mdictService.findByTypeAndValue(type,value);
		if(null != dict){
			if(StringUtil.isEmpty(id)){
				return "false";
			}else{
				if(!id.equals(dict.getId())){
					return "false";
				}
			}
		}
		return "true";
	}
	
}