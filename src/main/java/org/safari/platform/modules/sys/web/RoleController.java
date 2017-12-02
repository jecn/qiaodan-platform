package org.safari.platform.modules.sys.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.safari.platform.common.config.Global;
import org.safari.platform.common.utils.PageUtil;
import org.safari.platform.common.utils.StringUtil;
import org.safari.platform.common.web.BaseController;
import org.safari.platform.modules.sys.entity.Role;
import org.safari.platform.modules.sys.entity.User;
import org.safari.platform.modules.sys.service.RolePrivilegeService;
import org.safari.platform.modules.sys.service.RoleService;
import org.safari.platform.modules.sys.service.UserRoleService;
import org.safari.platform.modules.sys.utils.UserUtil;
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
 * 角色controller
 * @author Alitalk
 * @date 2015年1月13日
 */
@Controller
@RequestMapping("${adminPath}/sys/role")
public class RoleController extends BaseController{
	
	@Autowired
	private RoleService mroleService;
	
	@Autowired
	private RolePrivilegeService mrolePrivilegeService;
	
	@Autowired
	private UserRoleService muserRoleService;
	
	/**
	 * 默认页面
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(){
		return "sys/roleList";
	}
	
	/**
	 * 查看角色信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") String id, Model model) {
		model.addAttribute("role", mroleService.get(id));
		return "sys/roleView";
	}
	
	/**
	 * 角色集合(JSON)
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="list/{useable}",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(@PathVariable("useable") String useable,Role role,HttpServletRequest request) {
		PageUtil<Role> page = new PageUtil<Role>();
		role.page(request);
		if("-1".equals(useable)){
			role.setUseable("");
		}else{
			role.setUseable(useable);
		}
		mroleService.findPage(page,role);
		
		return resultData(page);
	}
	
	/**
	 * 获取角色拥有的权限ID集合
	 * @param roleId
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="{roleId}/json",method = RequestMethod.GET)
	@ResponseBody
	public List<String> getRolePrivileges(@PathVariable("roleId") String roleId){
		List<String> list = mrolePrivilegeService.getPrivilegeIdsByRoleId(roleId);
		return list;
	}
	
	/**
	 * 修改角色权限
	 * @param id
	 * @param newRoleList
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "{roleId}/updatePrivilege")
	@ResponseBody
	public String updatePrivilege(@PathVariable("roleId") String roleId,@RequestBody 
		List<String> privilegeList,HttpSession session){
		try {
			mrolePrivilegeService.updateRolePrivilege(roleId, privilegeList);
		} catch (Exception e) {
			LOG.error("修改角色权限失败",e);
			e.printStackTrace();
			return "fail";
		}
		
		// 去掉缓存
		List<String> userIds = muserRoleService.getUserIdList(roleId);
		for (String userId : userIds) {
			UserUtil.clearCache(new User(userId));
		}
		return "success";
	}
	
	/**
	 * 添加角色跳转
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("role", new Role());
		return "sys/roleForm";
	}

	/**
	 * 修改角色跳转
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id, Model model) {
		model.addAttribute("role", mroleService.get(id));
		return "sys/roleForm";
	}

	/**
	 * 保存角色
	 * @param role
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public String save(@Valid @ModelAttribute("role") Role role,Model model) {
		try {
			if("false".equals(checkCname(role.getId(), role.getCname()))){
				return "fail";
			}
			
			if("false".equals(checkEname(role.getId(), role.getEname()))){
				return "fail";
			}
			mroleService.save(role);
		} catch (Exception e) {
			LOG.error("角色保存失败",e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") String id) {
		try {
			Role role = new Role(id);
			role.setDelFlag(Global.DEL_YES);
			mroleService.delete(role);
		} catch (Exception e) {
			LOG.error("角色删除失败",e);
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
			Role role = mroleService.get(id);
			role.setUseable(useable);
			mroleService.save(role);
		} catch (Exception e) {
			LOG.error("角色操作失败",e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	/**
	 * Ajax请求校验cname是否唯一。
	 */
	@RequestMapping(value = "checkCname")
	@ResponseBody
	public String checkCname(String id,String cname) {
		Role role = mroleService.findUnique("cname",cname);
		if(null != role){
			if(StringUtil.isEmpty(id)){
				return "false";
			}else{
				if(!id.equals(role.getId())){
					return "false";
				}
			}
		}
		return "true";
	}
	
	/**
	 * Ajax请求校验ename是否唯一。
	 */
	@RequestMapping(value = "checkEname")
	@ResponseBody
	public String checkEname(String id,String ename) {
		Role role = mroleService.findUnique("ename",ename);
		if(null != role){
			if(StringUtil.isEmpty(id)){
				return "false";
			}else{
				if(!id.equals(role.getId())){
					return "false";
				}
			}
		}
		return "true";
	}
}
