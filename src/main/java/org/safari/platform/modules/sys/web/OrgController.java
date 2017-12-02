package org.safari.platform.modules.sys.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.safari.platform.common.config.Global;
import org.safari.platform.common.utils.StringUtil;
import org.safari.platform.common.web.BaseController;
import org.safari.platform.modules.sys.entity.Org;
import org.safari.platform.modules.sys.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 机构信息controller
 * @author Alitalk
 * @date 2017-02-17
 */
@Controller
@RequestMapping("${adminPath}/sys/org")
public class OrgController extends BaseController{

	@Autowired
	private OrgService morgService;

	/**
	 * 默认页面
	 */
	@RequiresPermissions("user")
	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "sys/orgList";
	}
	
	/**
	 * 查看机构信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") String id, Model model) {
		model.addAttribute("org", morgService.get(id));
		return "sys/orgView";
	}
	
	/**
	 * 获取机构信息tree
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="tree/{useable}",method = RequestMethod.GET)
	@ResponseBody
	public List<Org> tree(@PathVariable("useable") String useable,HttpServletRequest request) {
		Org org = new Org();
		if("-1".equals(useable)){
			org.setUseable("");
		}else{
			org.setUseable(useable);
		}
		return morgService.findAll(new Org());
	}
	
	/**
	 * 获取机构信息json
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="json/{useable}",method = RequestMethod.GET)
	@ResponseBody
	public List<Org> json(@PathVariable("useable") String useable,HttpServletRequest request) {
		Org org = new Org();
		if("-1".equals(useable)){
			org.setUseable("");
		}else{
			org.setUseable(useable);
		}
		return morgService.findAll(new Org());
	}
	
	/**
	 * 添加机构信息跳转
	 * @param model
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model, String pid) {
		Org org = new Org();
		org.setPid(pid);
		org.setParent(morgService.get(pid));
		model.addAttribute("org", org);
		return "sys/orgForm";
	}

	/**
	 * 修改机构信息跳转
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id, Model model) {
		model.addAttribute("org", morgService.get(id));
		return "sys/orgForm";
	}

	/**
	 * 保存机构信息
	 * 
	 * @param org
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public String save(@Valid @ModelAttribute("org")Org org,Model model) {
		try {
			morgService.save(org);
		} catch (Exception e) {
			LOG.error("机构保存失败",e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	/**
	 * 删除机构信息
	 * @param id
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") String id) {
		try {
			Org org = new Org(id);
			org.setDelFlag(Global.DEL_YES);
			morgService.deleteSubParentId(org);
		} catch (Exception e) {
			LOG.error("机构删除失败",e);
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
			Org org = new Org(id);
			org.setUseable(useable);
			morgService.updateSubParentId(org);
		} catch (Exception e) {
			LOG.error("机构操作失败",e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	/**
	 * 获取机构信息tree
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="treeSelect/{useable}",method = RequestMethod.GET)
	public String treeSelect(@PathVariable("useable") String useable,String type,
			String iframeId,Model model) {
		Org org = new Org();
		org.setType(type);
		if("-1".equals(useable)){
			org.setUseable("");
		}else{
			org.setUseable(useable);
		}
		
		JSONArray array = new JSONArray();
		List<Org> list = morgService.findAllTree(org);
		for (int i=0; i<list.size(); i++){
			Org e = list.get(i);
			if (StringUtil.isBlank(type) || (type!=null && type.equals(e.getType()))){
				JSONObject json = new JSONObject();
				json.put("id", e.getId());
				json.put("pid", e.getPid());
				json.put("name", e.getName());
				array.add(json);
			}
		}
		model.addAttribute("data", array.toString());
		model.addAttribute("iframeId", iframeId);
		return "sys/orgTree";
	}
	
}
