package org.safari.platform.modules.shoes.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.safari.platform.common.config.Global;
import org.safari.platform.common.utils.PageUtil;
import org.safari.platform.common.web.BaseController;
import org.safari.platform.modules.shoes.entity.ShoesRecm;
import org.safari.platform.modules.shoes.service.ShoesRecmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 球鞋推荐controller
 * @author Alitalk
 * @date 2017-02-17
 */
@Controller
@RequestMapping("${adminPath}/shoes/recm")
public class ShoesRecmController extends BaseController{

	@Autowired
	private ShoesRecmService mshoesRecmService;
	

	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "shoes/recmList";
	}
	
	/**
	 * 查看球鞋推荐信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") String id, Model model) {
		model.addAttribute("recm", mshoesRecmService.get(id));
		return "shoes/recmView";
	}
	
	/**
	 * 获取分页球鞋信息
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="list",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, ShoesRecm recm) {
		recm.page(request);
		
		PageUtil<ShoesRecm> page = new PageUtil<ShoesRecm>();
		mshoesRecmService.findPage(page,recm);
		return resultData(page);
	}
	
	/**
	 * 添加球鞋推荐信息跳转
	 * @param model
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("recm", new ShoesRecm());
		return "shoes/recmForm";
	}
	
	/**
	 * 修改球鞋推荐信息跳转
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id, Model model) {
		model.addAttribute("recm", mshoesRecmService.get(id));
		return "shoes/recmForm";
	}

	/**
	 * 保存球鞋推荐信息
	 * @param recm
	 * @param model
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public String save(@Valid @ModelAttribute("recm")ShoesRecm recm,Model model) {
		try {
			mshoesRecmService.save(recm);
		} catch (Exception e) {
			LOG.error("球鞋推荐保存失败",e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	/**
	 * 删除球鞋推荐信息
	 * @param id
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") String id) {
		try {
			ShoesRecm recm = new ShoesRecm(id);
			recm.setDelFlag(Global.DEL_YES);
			mshoesRecmService.delete(recm);
		} catch (Exception e) {
			LOG.error("球鞋推荐删除失败",e);
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
			ShoesRecm recm = new ShoesRecm(id);
			recm.setUseable(useable);
			mshoesRecmService.updateUseable(recm);
		} catch (Exception e) {
			LOG.error("球鞋推荐操作失败",e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
}
