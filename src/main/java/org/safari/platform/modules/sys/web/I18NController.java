package org.safari.platform.modules.sys.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.safari.platform.common.config.Global;
import org.safari.platform.common.utils.PageUtil;
import org.safari.platform.common.utils.StringUtil;
import org.safari.platform.common.web.BaseController;
import org.safari.platform.modules.sys.entity.I18N;
import org.safari.platform.modules.sys.service.I18NService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>Title: 国际化</p>
 * <p>Description: </p>
 * <p>Company: 深圳市萨法瑞科技有限公司</p>
 * @author Alitalk 
 * @date 2016年5月20日
*/
@Controller
@RequestMapping("${adminPath}/sys/i18n")
public class I18NController extends BaseController{
	
	@Autowired
	private I18NService mI18NService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "sys/i18nList";
	}
	
	/**
	 * 查看国际化信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") String id, Model model) {
		model.addAttribute("i18n", mI18NService.get(id));
		return "sys/i18nView";
	}

	/**
	 * 获取分页国际化
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="list",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, I18N i18n) {
		i18n.page(request);
		
		PageUtil<I18N> page = new PageUtil<I18N>();
		mI18NService.findPage(page,i18n);
		return resultData(page);
	}
	
	/**
	 * 添加国际化跳转
	 * @param model
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("i18n", new I18N());
		return "sys/i18nForm";
	}
	
	/**
	 * 修改国际化跳转
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id, Model model) {
		model.addAttribute("i18n", mI18NService.get(id));
		return "sys/i18nForm";
	}

	/**
	 * 保存国际化
	 * @param i18n
	 * @param model
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public String save(@Valid I18N i18n, Model model) {
		try {
			if("false".equals(checkKey(i18n.getId(), i18n.getLangCode(), i18n.getLangKey()))){
				return "fail";
			}
			mI18NService.save(i18n);
		} catch (Exception e) {
			LOG.error("国际化保存失败",e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	/**
	 * 删除国际化
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") String id) {
		try {
			I18N i18n = new I18N(id);
			i18n.setDelFlag(Global.DEL_YES);
			mI18NService.delete(i18n);
		} catch (Exception e) {
			LOG.error("国际化删除失败",e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	/**
	 * Ajax请求校验langKey是否唯一。
	 */
	@RequestMapping(value = "checkKey")
	@ResponseBody
	public String checkKey(String id,String langCode,String langKey) {
		I18N i18n = mI18NService.findByCodeAndKey(langCode,langKey);
		if(null != i18n){
			if(StringUtil.isEmpty(id)){
				return "false";
			}else{
				if(!id.equals(i18n.getId())){
					return "false";
				}
			}
		}
		return "true";
	}
}
