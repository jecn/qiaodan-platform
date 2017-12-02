package org.safari.platform.modules.sys.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.safari.platform.common.config.Global;
import org.safari.platform.common.utils.PageUtil;
import org.safari.platform.common.utils.StringUtil;
import org.safari.platform.common.utils.security.Digests;
import org.safari.platform.common.utils.security.Encodes;
import org.safari.platform.common.web.BaseController;
import org.safari.platform.modules.sys.entity.User;
import org.safari.platform.modules.sys.service.UserOrgService;
import org.safari.platform.modules.sys.service.UserRoleService;
import org.safari.platform.modules.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户controller
 * @author Alitalk
 * @date 2015年1月13日
 */
@Controller
@RequestMapping("${adminPath}/sys/user")
public class UserController extends BaseController {

	@Autowired
	private UserService muserService;

	@Autowired
	private UserRoleService muserRoleService;
	
	@Autowired
	private UserOrgService muserOrgService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "sys/userList";
	}
	
	/**
	 * 查看用户跳转
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") String id, Model model) {
		model.addAttribute("user", muserService.get(id));
		return "sys/userView";
	}

	/**
	 * 分页获取用户
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="list",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, 
			HttpServletResponse response, User user) {
		user.page(request);
		PageUtil<User> page = new PageUtil<User>();
		muserService.findPage(page,user);
		return resultData(page);
	}

	/**
	 * 添加用户跳转
	 * @param model
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("user", new User());
		return "sys/userForm";
	}
	
	/**
	 * 修改用户跳转
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id, Model model) {
		model.addAttribute("user", muserService.get(id));
		return "sys/userForm";
	}

	/**
	 * 保存用户
	 * 
	 * @param user
	 * @param model
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public String save(@Valid @ModelAttribute("user")User user, Model model) {
		if(StringUtil.isEmpty(user.getId())){ //新增
			user.setPassword("123456");
			entryptPassword(user);
		}
		try {
			if("false".equals(checkUsername(user.getId(), user.getUsername()))){
				return "fail";
			}
			muserService.save(user);
		} catch (Exception e) {
			LOG.error("用户保存失败",e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") String id) {
		try {
			User user = new User(id);
			user.setDelFlag(Global.DEL_YES);
			muserService.delete(user);
		} catch (Exception e) {
			LOG.error("用户删除失败",e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	/**
	 * 弹窗页-用户拥有的角色
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "{userId}/userRole")
	public String getUserRole(@PathVariable("userId") String userId, Model model) {
		model.addAttribute("userId", userId);
		return "sys/userRoleList";
	}
	
	/**
	 * 获取用户拥有的角色ID集合
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "{userId}/role")
	@ResponseBody
	public List<String> getRoleIdList(@PathVariable("userId") String userId) {
		return muserRoleService.getRoleIdList(userId);
	}
	
	/**
	 * 修改用户拥有的角色
	 * 
	 * @param id
	 * @param newRoleList
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "{userId}/updateRole")
	@ResponseBody
	public String updateUserRole(@PathVariable("userId") String userId,@RequestBody List<String> roleList) {
		try {
			muserRoleService.updateUserRole(userId, roleList);
		} catch (Exception e) {
			LOG.error("修改用户角色失败",e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	/**
	 * 弹窗页-用户所在机构
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "{userId}/userOrg")
	public String getUserOrg(@PathVariable("userId") String userId, Model model) {
		model.addAttribute("userId", userId);
		return "sys/userOrgList";
	}

	/**
	 * 获取用户拥有的机构ID集合
	 * @param id
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "{userId}/org")
	@ResponseBody
	public List<String> getOrgIdList(@PathVariable("userId") String userId) {
		return muserOrgService.getOrgIdList(userId);
	}
	
	/**
	 * 修改用户所在的机构
	 * 
	 * @param id
	 * @param newRoleList
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "{userId}/updateOrg")
	@ResponseBody
	public String updateUserOrg(@PathVariable("userId") String userId,@RequestBody List<String> orgList) {
		try {
			muserOrgService.updateUserOrg(userId,orgList);
		} catch (Exception e) {
			LOG.error("修改用户机构失败",e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	/**
	 * 修改密码跳转
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "updatePwd", method = RequestMethod.GET)
	public String updatePwdForm(Model model, HttpSession session) {
		model.addAttribute("user", (User) session.getAttribute("user"));
		return "sys/updatePwd";
	}

	/**
	 * 修改密码
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "updatePwd", method = RequestMethod.POST)
	@ResponseBody
	public String updatePwd(String oldPassword,@Valid @ModelAttribute @RequestBody User user, HttpSession session) {
		
		if (muserService.checkPassword((User) session.getAttribute("user"),oldPassword)) {
			try {
				muserService.updatePwd(user);
				session.setAttribute("user", user);
			} catch (Exception e) {
				LOG.error("用户修改密码失败",e);
				e.printStackTrace();
				return "fail";
			}
			return "success";
		} else {
			return "fail";
		}

	}
	
	/**
	 * 重置密码
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "resetpw/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String resetpw(@PathVariable("id")String id) {
		User user = new User(id);
		user.setPassword("123456");
		entryptPassword(user);
		try {
			muserService.updatePwd(user);
		} catch (Exception e) {
			LOG.error("重置密码失败",e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	/**
	 * Ajax请求校验username是否唯一。
	 */
	@RequestMapping(value = "checkUsername")
	@ResponseBody
	public String checkUsername(String id,String username) {
		User user = muserService.findUnique("username",username);
		if(null != user){
			if(StringUtil.isEmpty(id)){
				return "false";
			}else{
				if(!id.equals(user.getId())){
					return "false";
				}
			}
		}
		return "true";
	}

	/**
	 * ajax请求校验原密码是否正确
	 * 
	 * @param oldPassword
	 * @param request
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "checkPwd")
	@ResponseBody
	public String checkPwd(String oldPassword, HttpSession session) {
		if (muserService.checkPassword((User) session.getAttribute("user"),oldPassword)) {
			return "true";
		} else {
			return "false";
		}
	}

	
	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(User user) {
		byte[] salt = Digests.generateSalt(Global.SHIRO_SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] password = Digests.sha1(user.getPassword().getBytes(),salt, Global.SHIRO_HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(password));
	}
	
}