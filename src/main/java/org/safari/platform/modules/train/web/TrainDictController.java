package org.safari.platform.modules.train.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.safari.platform.common.config.Global;
import org.safari.platform.common.utils.PageUtil;
import org.safari.platform.common.utils.StringUtil;
import org.safari.platform.common.web.BaseController;
import org.safari.platform.modules.train.entity.TrainDict;
import org.safari.platform.modules.train.service.TrainDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 训练字典controller
 * @author Alitalk
 * @date 2017-02-17
 */
@Controller
@RequestMapping("${adminPath}/train/dict")
public class TrainDictController extends BaseController{

	@Autowired
	private TrainDictService mtrainDictService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "train/dictList";
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
		model.addAttribute("dict", mtrainDictService.get(id));
		return "train/dictView";
	}

	/**
	 * 获取分页训练字典信息
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="list",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, TrainDict dict) {
		dict.page(request);
		
		PageUtil<TrainDict> page = new PageUtil<TrainDict>();
		mtrainDictService.findPage(page,dict);
		
		return resultData(page);
	}
	
	/**
	 * 添加训练字典跳转
	 * @param model
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("dict", new TrainDict());
		return "train/dictForm";
	}
	
	/**
	 * 修改训练字典信息跳转
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id, Model model) {
		model.addAttribute("dict", mtrainDictService.get(id));
		return "train/dictForm";
	}

	/**
	 * 保存训练字典信息
	 * @param dict
	 * @param model
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public String save(@Valid @ModelAttribute("dict")TrainDict dict,Model model) {
		try {
			mtrainDictService.save(dict);
		} catch (Exception e) {
			LOG.error("训练字典保存失败",e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	/**
	 * 删除训练字典信息
	 * @param id
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") String id) {
		try {
			TrainDict dict = new TrainDict(id);
			dict.setDelFlag(Global.DEL_YES);
			mtrainDictService.delete(dict);
		} catch (Exception e) {
			LOG.error("训练字典删除失败",e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	/**
	 * Ajax请求校验是否唯一。
	 */
	@RequestMapping(value = "checkNameAndType")
	@ResponseBody
	public String checkNameAndType(String id,String name,String type) {
		TrainDict dict = mtrainDictService.findByNameAndType(name,type);
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
