package org.safari.platform.modules.sys.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.safari.platform.common.config.Global;
import org.safari.platform.common.utils.PageUtil;
import org.safari.platform.common.utils.StringUtil;
import org.safari.platform.common.web.BaseController;
import org.safari.platform.modules.sys.entity.Corp;
import org.safari.platform.modules.sys.service.CorpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 单位controller
 * @author Alitalk
 * @date 2017-03-30
 */
@Controller
@RequestMapping("${adminPath}/sys/corp")
public class CorpController extends BaseController{
	
	@Autowired
	private CorpService mcorpService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "sys/corpList";
	}
	
	/**
	 * 查看单位信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") String id, Model model) {
		model.addAttribute("crop", mcorpService.get(id));
		return "sys/corpView";
	}

	/**
	 * 获取字典json
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="list",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request,Corp corp) {
		corp.page(request);
		PageUtil<Corp> page = new PageUtil<Corp>();
		mcorpService.findPage(page,corp);
		
		return resultData(page);
	}
	
	/**
	 * 添加单位跳转
	 * @param model
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("corp", new Corp());
		return "sys/corpForm";
	}
	
	/**
	 * 修改单位跳转
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id, Model model) {
		model.addAttribute("corp", mcorpService.get(id));
		return "sys/corpForm";
	}

	/**
	 * 保存单位信息
	 * 
	 * @param corp
	 * @param model
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public String save(@Valid @ModelAttribute("corp")Corp corp, Model model) {
		try {
			if("false".equals(checkName(corp.getId(), corp.getName()))){
				return "fail";
			}
			mcorpService.save(corp);
		} catch (Exception e) {
			LOG.error("单位保存失败",e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	/**
	 * 删除单位
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") String id) {
		try {
			Corp corp = new Corp(id);
			corp.setDelFlag(Global.DEL_YES);
			mcorpService.delete(corp);
		} catch (Exception e) {
			LOG.error("单位删除失败",e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	/**
	 * Ajax请求校验name是否唯一。
	 */
	@RequestMapping(value = "checkName")
	@ResponseBody
	public String checkName(String id,String name) {
		Corp corp = mcorpService.findUnique("name",name);
		if(null != corp){
			if(StringUtil.isEmpty(id)){
				return "false";
			}else{
				if(!id.equals(corp.getId())){
					return "false";
				}
			}
		}
		return "true";
	}
}
