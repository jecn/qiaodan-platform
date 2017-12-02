package org.safari.platform.modules.rept.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.jfree.chart.servlet.ServletUtilities;
import org.safari.platform.modules.rept.util.ReportUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("${adminPath}/rept/test")
public class TestController {

	@RequestMapping(method = RequestMethod.GET)
	public String view(HttpServletRequest request, Model model) {
		//保存图片 返回图片文件名
		String filename = null;
		try {
			filename = ServletUtilities.saveChartAsPNG(ReportUtil.lineChart(ReportUtil.createDataset(),
					"新增用户量","月份","数据量"), 600, 400, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//获取图片路径（内存中）
		String graphURL = request.getContextPath() + "/DisplayChart?filename="   + filename;
		String image=  "<img src='" 
		            + graphURL 
		            + "' width=600 height=400 border=0 usemap='#" 
		            + filename + "'/>";    
		model.addAttribute("image",image);
		return "rept/test";
	}
}
