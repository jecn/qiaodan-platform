package org.safari.platform.modules.sys.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.safari.platform.common.config.Global;
import org.safari.platform.common.utils.CacheUtil;
import org.safari.platform.common.utils.StringUtil;
import org.safari.platform.common.web.BaseController;
import org.safari.platform.modules.sys.entity.Privilege;
import org.safari.platform.modules.sys.entity.User;
import org.safari.platform.modules.sys.service.PrivilegeService;
import org.safari.platform.modules.sys.service.RolePrivilegeService;
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
 * 权限controller
 * @author Alitalk
 * @date 2015年1月13日
 */
@Controller
@RequestMapping("${adminPath}/sys/privilege")
public class PrivilegeController extends BaseController{
	
	@Autowired
	private PrivilegeService mprivilegeService;
	
	@Autowired
	private RolePrivilegeService mrolePrivilegeService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(){
		return "sys/privilegeList";
	}
	
	/**
	 * 查看权限信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") String id, Model model) {
		model.addAttribute("privilege", mprivilegeService.get(id));
		return "sys/privilegeView";
	}
	
	/**
	 * 添加权限跳转
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model, String pid) {
		Privilege privilege = new Privilege();
		privilege.setPid(pid);
		privilege.setParent(mprivilegeService.get(pid));
		model.addAttribute("privilege", privilege);
		return "sys/privilegeForm";
	}
	
	/**
	 * 修改权限跳转
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id, Model model) {
		model.addAttribute("privilege", mprivilegeService.get(id));
		return "sys/privilegeForm";
	}
	
	/**
	 * 修改权限/菜单
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public String save(@Valid @ModelAttribute("privilege")Privilege privilege, Model model) {
		try {
			if("false".equals(checkName(privilege.getId(), privilege.getPid(), privilege.getName()))){
				return "fail";
			}
			if("false".equals(checkCode(privilege.getId(), privilege.getCode()))){
				return "fail";
			}
			
			mprivilegeService.save(privilege);
		} catch (Exception e) {
			LOG.error("权限保存失败",e);
			e.printStackTrace();
			return "fail";
		}
		
		//去掉缓存
		CacheUtil.remove(SysUtil.CACHE_PRIVILEGE_ALL);
		UserUtil.clearAllUserPrivilegeCache();
		return "success";
	}

	/**
	 * 删除权限信息
	 * @param id
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") String id) {
		try {
			
			Privilege privilege = new Privilege(id);
			privilege.setDelFlag(Global.DEL_YES);
			mprivilegeService.deleteSubParentId(privilege);
		} catch (Exception e) {
			LOG.error("权限删除失败",e);
			e.printStackTrace();
			return "fail";
		}
		
		//去掉缓存
		CacheUtil.remove(SysUtil.CACHE_PRIVILEGE_ALL);
		UserUtil.clearAllUserPrivilegeCache();
		return "success";
	}
	
	/**
	 * 权限集合 类型Tree
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="allTree",method = RequestMethod.GET)
	@ResponseBody
	public List<Privilege> allTree(){
		List<Privilege> list = SysUtil.getPrivilegeAllList();
		return list;
	}
	
	/**
	 * 权限集合 类型Tree
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="tree",method = RequestMethod.GET)
	@ResponseBody
	public List<Privilege> tree(){
		List<Privilege> list = UserUtil.getPrivilegeTree();
		return list;
	}
	
	/**
	 * 权限集合 类型JSON
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="json",method = RequestMethod.GET)
	@ResponseBody
	public List<Privilege> json(){
		List<Privilege> list = UserUtil.getPrivilegeList();
		return list;
	}
	
	/**
	 * 权限集合(JSON)
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="{id}/json",method = RequestMethod.GET)
	@ResponseBody
	public List<Privilege> getData(@PathVariable("id") String id) {
		List<Privilege> list = new ArrayList<Privilege>();
		if(!StringUtil.isEmpty(id) && !"0".equals(id)){
			list = mprivilegeService.findSubByParentId(new Privilege(id,null));
		}else{
			list = SysUtil.getPrivilegeAllList();
		}
		return list;
	}
	
	/**
	 * 当前登录用户的菜单权限集合
	 */
	@RequiresPermissions("user")
	@RequestMapping("my/menu")
	@ResponseBody
	public String myMenu() {
		return UserUtil.getMenu();
	}
	
	/**
	 * 某用户的权限集合
	 */
	@RequiresPermissions("user")
	@RequestMapping("{userId}/json")
	@ResponseBody
	public List<Privilege> userPrivilege(@PathVariable("userId") String userId) {
		List<Privilege> list = UserUtil.getPrivilegeList(new User(userId));
		return list;
	}
	
	/**
	 * 获取用户拥有权限的工具选项
	 * @param linsence String
	 * @return String
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="toolbar/{linsence}",method = RequestMethod.GET)
	@ResponseBody
	public String toolbar(@PathVariable("linsence") String linsence) {
		return UserUtil.getBar(linsence);
	}
	
	/**
	 * 获取用户拥有权限的表格列选项
	 * @date 2016-6-1
	 * @param linsence String
	 * @return String
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="cols/{linsence}",method = RequestMethod.GET)
	@ResponseBody
	public String columns(@PathVariable("linsence") String linsence) {
		return UserUtil.getCols(linsence);
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
			Privilege privilege = new Privilege(id);
			privilege.setUseable(useable);
			mprivilegeService.updateSubParentId(privilege);
		} catch (Exception e) {
			LOG.error("权限操作失败",e);
			e.printStackTrace();
			return "fail";
		}
		
		//去掉缓存
		CacheUtil.remove(SysUtil.CACHE_PRIVILEGE_ALL);
		UserUtil.clearAllUserPrivilegeCache();
		return "success";
	}
	
	/**
	 * Ajax请求校验name是否唯一。
	 */
	@RequestMapping(value = "checkName")
	@ResponseBody
	public String checkName(String id,String pid,String name) {
		if(StringUtil.isEmpty(pid)){
			pid = "0";
		}
		
		Privilege privilege = mprivilegeService.findByPidAndName(pid,name);
		if(null != privilege){
			if(StringUtil.isEmpty(id)){
				return "false";
			}else{
				if(!id.equals(privilege.getId())){
					return "false";
				}
			}
		}
		return "true";
	}
	
	/**
	 * Ajax请求校验code是否唯一。
	 */
	@RequestMapping(value = "checkCode")
	@ResponseBody
	public String checkCode(String id,String code) {
		if(StringUtil.isEmpty(code)){
			return "true";
		}
		Privilege privilege = mprivilegeService.findUnique("code",code);
		if(null != privilege){
			if(StringUtil.isEmpty(id)){
				return "false";
			}else{
				if(!id.equals(privilege.getId())){
					return "false";
				}
			}
		}
		return "true";
	}
	
	/**
	 * 获取机构信息tree
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="treeSelect/{useable}",method = RequestMethod.GET)
	public String treeSelect(@PathVariable("useable") String useable,String type,
			String iframeId,Model model) {
		Privilege privilege = new Privilege();
		privilege.setType(type);
		if("-1".equals(useable)){
			privilege.setUseable("");
		}else{
			privilege.setUseable(useable);
		}
		
		JSONArray array = new JSONArray();
		List<Privilege> list = mprivilegeService.findAllTree(privilege);
		for (int i=0; i<list.size(); i++){
			Privilege e = list.get(i);
			if (StringUtil.isBlank(type) || (type!=null && type.contains(e.getType()))){
				JSONObject json = new JSONObject();
				json.put("id", e.getId());
				json.put("pid", e.getPid());
				json.put("name", e.getName());
				array.add(json);
			}
		}
		model.addAttribute("data", array.toString());
		model.addAttribute("iframeId", iframeId);
		return "sys/privilegeTree";
	}
	
}
