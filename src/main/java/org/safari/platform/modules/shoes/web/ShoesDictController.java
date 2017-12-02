package org.safari.platform.modules.shoes.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.safari.platform.common.config.Global;
import org.safari.platform.common.utils.CacheUtil;
import org.safari.platform.common.utils.PageUtil;
import org.safari.platform.common.utils.StringUtil;
import org.safari.platform.common.web.BaseController;
import org.safari.platform.modules.shoes.entity.ShoesDict;
import org.safari.platform.modules.shoes.service.ShoesDictService;
import org.safari.platform.modules.shoes.utils.ShoesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 球鞋字典controller
 * @author Alitalk
 * @date 2017-02-17
 */
@Controller
@RequestMapping("${adminPath}/shoes/dict")
public class ShoesDictController extends BaseController{

	@Autowired
	private ShoesDictService mshoesDictService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "shoes/dictList";
	}
	
	/**
	 * 查看球鞋信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") String id, Model model) {
		model.addAttribute("dict", mshoesDictService.get(id));
		return "shoes/dictView";
	}

	/**
	 * 获取分页球鞋字典信息
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="list",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, ShoesDict dict) {
		dict.page(request);
		
		PageUtil<ShoesDict> page = new PageUtil<ShoesDict>();
		mshoesDictService.findPage(page,dict);
		
		return resultData(page);
	}
	
	/**
	 * 添加球鞋字典跳转
	 * @param model
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("dict", new ShoesDict());
		return "shoes/dictForm";
	}
	
	/**
	 * 修改球鞋字典信息跳转
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id, Model model) {
		model.addAttribute("dict", mshoesDictService.get(id));
		return "shoes/dictForm";
	}

	/**
	 * 保存球鞋字典信息
	 * @param dict
	 * @param model
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public String save(@Valid @ModelAttribute("dict")ShoesDict dict,Model model) {
		try {
			if("false".equals(checkLabel(dict.getId(), dict.getType(), dict.getLabel()))){
				return "fail";
			}
			
			mshoesDictService.save(dict);
			CacheUtil.remove(ShoesUtil.CACHE_DICT_MAP);
		} catch (Exception e) {
			LOG.error("球鞋字典保存失败",e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	/**
	 * 删除球鞋字典信息
	 * @param id
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") String id) {
		try {
			ShoesDict dict = new ShoesDict(id);
			dict.setDelFlag(Global.DEL_YES);
			mshoesDictService.delete(dict);
		} catch (Exception e) {
			LOG.error("球鞋字典删除失败",e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	/**
	 * 启用/禁用
	 * @param id
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "{id}/opt")
	@ResponseBody
	public String opt(@PathVariable("id") String id,String useable) {
		try {
			ShoesDict dict = new ShoesDict(id);
			dict.setUseable(useable);
			mshoesDictService.updateUseable(dict);
		} catch (Exception e) {
			LOG.error("球鞋字典操作失败",e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	/**
	 * Ajax请求校验value是否唯一。
	 */
	@RequestMapping(value = "checkLabel")
	@ResponseBody
	public String checkLabel(String id,String type,String label) {
		ShoesDict dict = mshoesDictService.findByTypeAndLabel(type,label);
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
