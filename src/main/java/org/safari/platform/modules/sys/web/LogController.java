package org.safari.platform.modules.sys.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.safari.platform.common.utils.PageUtil;
import org.safari.platform.common.web.BaseController;
import org.safari.platform.modules.sys.entity.Log;
import org.safari.platform.modules.sys.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 日志controller
 * @author Alitalk
 * @date 2017-02-17
 */
@Controller
@RequestMapping("${adminPath}/sys/log")
public class LogController extends BaseController{

	@Autowired
	private LogService mlogService;
	
	/**
	 * 默认页面
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(){
		return "sys/logList";
	}
	
	/**
	 * 获取日志json
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="list",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request,Log log) {
		PageUtil<Log> page = new PageUtil<Log>();
		
		mlogService.findPage(page,log);
		return resultData(page);
	}
	
	/**
	 * 删除日志
	 * @param id
	 */
	@RequiresPermissions("sys:log:delete")
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") String id) {
		mlogService.delete(new Log(id));
		return "success";
	}
	
	/**
	 * 批量删除日志
	 * @param idList
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestBody List<String> idList) {
		mlogService.delete(idList);
		return "success";
	}

	/**
	 * 导出excel
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception{
		/*response.setContentType("application/msexcel;charset=GBK");
        
        List<Log> list = mlogService.getAll();//获取数据
        
        String title = "log";
        String[] hearders = new String[] {"操作编码", "详细描述", "执行时间(mm)", "操作系统", "浏览器", "IP","MAC","操作者","操作时间"};//表头数组
        String[] fields = new String[] {"operationCode", "description", "executeTime", "os", "browser", "ip","mac","creater","createDate"};//People对象属性数组
        TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders),fields);
        JsGridReportBase report = new JsGridReportBase(request, response);
        report.exportToExcel(title, "admin", td);*/
	}
}
