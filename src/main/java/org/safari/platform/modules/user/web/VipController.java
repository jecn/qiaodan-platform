package org.safari.platform.modules.user.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.safari.platform.common.utils.MD5Util;
import org.safari.platform.common.utils.PageUtil;
import org.safari.platform.common.web.BaseController;
import org.safari.platform.modules.user.entity.Vip;
import org.safari.platform.modules.user.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 会员controller
 * @author Alitalk
 * @date 2017-02-17
 */
@Controller
@RequestMapping("${adminPath}/user/vip")
public class VipController extends BaseController{

	@Autowired
	private VipService mvipService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "user/vipList";
	}
	
	/**
	 * 查看训练信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") String id, Model model) {
		model.addAttribute("vip", mvipService.get(id));
		return "user/vipView";
	}

	/**
	 * 获取分页训练信息
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="list",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, Vip vip) {
		vip.page(request);
		
		PageUtil<Vip> page = new PageUtil<Vip>();
		mvipService.findPage(page,vip);
		return resultData(page);
	}
	
	/**
	 * 暂停使用/正常使用
	 * @param id
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "{id}/opt")
	@ResponseBody
	public String opt(@PathVariable("id") String id,String stat) {
		try {
			Vip vip = new Vip(id);
			vip.setStat(stat);
			mvipService.updateStat(vip);
		} catch (Exception e) {
			LOG.error("会员操作失败",e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	/**
	 * 重置密码
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "resetpw/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String resetpw(@PathVariable("id")String id) {
		String password = MD5Util.GetMD5Code("123456");
		try {
			mvipService.resetPassword(id,password);
		} catch (Exception e) {
			LOG.error("重置密码失败",e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
}
