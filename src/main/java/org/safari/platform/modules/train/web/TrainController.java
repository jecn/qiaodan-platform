package org.safari.platform.modules.train.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.safari.platform.common.config.Global;
import org.safari.platform.common.utils.PageUtil;
import org.safari.platform.common.web.BaseController;
import org.safari.platform.modules.train.entity.Train;
import org.safari.platform.modules.train.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 训练controller
 * @author Alitalk
 * @date 2017-02-17
 */
@Controller
@RequestMapping("${adminPath}/train/main")
public class TrainController extends BaseController{

	@Autowired
	private TrainService mtrainService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "train/trainList";
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
		model.addAttribute("train", mtrainService.get(id));
		return "train/trainView";
	}

	/**
	 * 获取分页训练信息
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="list",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, Train train) {
		train.page(request);
		
		PageUtil<Train> page = new PageUtil<Train>();
		mtrainService.findPage(page,train);
		return resultData(page);
	}
	
	/**
	 * 添加训练跳转
	 * @param model
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("train", new Train());
		return "train/trainForm";
	}
	
	/**
	 * 修改训练信息跳转
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id, Model model) {
		model.addAttribute("train", mtrainService.get(id));
		return "train/trainForm";
	}

	/**
	 * 保存训练信息
	 * @param shoes
	 * @param model
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public String save(@Valid @ModelAttribute("train")Train train,Model model) {
		try {
			mtrainService.save(train);
		} catch (Exception e) {
			LOG.error("训练保存失败",e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	/**
	 * 删除训练信息
	 * @param id
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") String id) {
		try {
			Train train = new Train(id);
			train.setDelFlag(Global.DEL_YES);
			mtrainService.delete(train);
		} catch (Exception e) {
			LOG.error("训练删除失败",e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
}
