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
import org.safari.platform.modules.sys.entity.Area;
import org.safari.platform.modules.sys.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 区域信息类型controller
 * @author Alitalk
 * @date 2017-02-17
 */
@Controller
@RequestMapping("${adminPath}/sys/area")
public class AreaController extends BaseController{

	@Autowired
	private AreaService mareaService;

	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "sys/areaList";
	}
	
	/**
	 * 查看区域信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") String id, Model model) {
		model.addAttribute("area", mareaService.get(id));
		return "sys/areaView";
	}
	
	/**
	 * 获取区域信息类型Tree
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="tree/{useable}",method = RequestMethod.GET)
	@ResponseBody
	public List<Area> tree(@PathVariable("useable") String useable,HttpServletRequest request) {
		Area area = new Area();
		if("-1".equals(useable)){
			area.setUseable("");
		}else{
			area.setUseable(useable);
		}
		return mareaService.findAll(area);
	}
	
	/**
	 * 获取区域信息类型json
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="json/{useable}",method = RequestMethod.GET)
	@ResponseBody
	public List<Area> json(@PathVariable("useable") String useable,HttpServletRequest request) {
		Area area = new Area();
		if("-1".equals(useable)){
			area.setUseable("");
		}else{
			area.setUseable(useable);
		}
		return mareaService.findAll(area);
	}
	
	/**
	 * 添加区域信息跳转
	 * @param model
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model,String pid) {
		Area area = new Area();
		area.setPid(pid);
		area.setParent(mareaService.get(pid));
		model.addAttribute("area", area);
		return "sys/areaForm";
	}
	
	/**
	 * 修改区域信息跳转
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id, Model model) {
		model.addAttribute("area", mareaService.get(id));
		return "sys/areaForm";
	}

	/**
	 * 保存区域信息
	 * @param area
	 * @param model
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public String save(@Valid @ModelAttribute("area")Area area,Model model) {
		try {
			mareaService.save(area);
		} catch (Exception e) {
			LOG.error("区域保存失败",e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	/**
	 * 删除区域信息
	 * @param id
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") String id) {
		try {
		Area area = new Area(id);
		area.setDelFlag(Global.DEL_YES);
		mareaService.deleteSubParentId(area);
		} catch (Exception e) {
			LOG.error("区域删除失败",e);
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
			Area area = new Area(id);
			area.setUseable(useable);
			mareaService.updateSubParentId(area);
		} catch (Exception e) {
			LOG.error("区域操作失败",e);
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
		Area area = new Area();
		area.setType(type);
		if("-1".equals(useable)){
			area.setUseable("");
		}else{
			area.setUseable(useable);
		}
		
		JSONArray array = new JSONArray();
		List<Area> list = mareaService.findAllTree(area);
		for (int i=0; i<list.size(); i++){
			Area e = list.get(i);
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
		return "sys/areaTree";
	}
	
}
