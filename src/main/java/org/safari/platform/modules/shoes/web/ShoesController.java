package org.safari.platform.modules.shoes.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.safari.platform.common.config.Global;
import org.safari.platform.common.utils.PageUtil;
import org.safari.platform.common.utils.StringUtil;
import org.safari.platform.common.web.BaseController;
import org.safari.platform.modules.shoes.entity.Shoes;
import org.safari.platform.modules.shoes.service.ShoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 球鞋controller
 * @author Alitalk
 * @date 2017-02-17
 */
@Controller
@RequestMapping("${adminPath}/shoes/main")
public class ShoesController extends BaseController{

	@Autowired
	private ShoesService mshoesService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "shoes/shoesList";
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
		model.addAttribute("shoes", mshoesService.get(id));
		return "shoes/shoesView";
	}

	/**
	 * 获取分页球鞋信息
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="list",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, Shoes shoes) {
		shoes.page(request);
		
		PageUtil<Shoes> page = new PageUtil<Shoes>();
		mshoesService.findPage(page,shoes);
		
		return resultData(page);
	}
	
	/**
	 * 添加球鞋跳转
	 * @param model
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("shoes", new Shoes());
		return "shoes/shoesForm";
	}
	
	/**
	 * 修改球鞋信息跳转
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id, Model model) {
		model.addAttribute("shoes", mshoesService.get(id));
		return "shoes/shoesForm";
	}

	/**
	 * 保存球鞋信息
	 * @param shoes
	 * @param model
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public String save(@Valid @ModelAttribute("shoes")Shoes shoes,Model model) {
		try {
			if("false".equals(checkCode(shoes.getId(), shoes.getCode()))){
				return "fail";
			}
			mshoesService.save(shoes);
		} catch (Exception e) {
			LOG.error("球鞋保存失败",e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	/**
	 * 删除球鞋信息
	 * @param id
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") String id) {
		try {
			Shoes shoes = new Shoes(id);
			shoes.setDelFlag(Global.DEL_YES);
			mshoesService.delete(shoes);
		} catch (Exception e) {
			LOG.error("球鞋删除失败",e);
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
			Shoes shoes = new Shoes(id);
			shoes.setUseable(useable);
			mshoesService.updateUseable(shoes);
		} catch (Exception e) {
			LOG.error("球鞋操作失败",e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	/**
	 * Ajax请求校验是否唯一。
	 */
	@RequestMapping(value = "checkCode")
	@ResponseBody
	public String checkCode(String id,String code) {
		Shoes shoes = mshoesService.findUnique("code",code);
		if(null != shoes){
			if(StringUtil.isEmpty(id)){
				return "false";
			}else{
				if(!id.equals(shoes.getId())){
					return "false";
				}
			}
		}
		return "true";
	}
	
}
