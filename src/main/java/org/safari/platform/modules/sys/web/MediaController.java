package org.safari.platform.modules.sys.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.safari.platform.common.enums.MediaEnum;
import org.safari.platform.common.utils.FileUtil;
import org.safari.platform.common.utils.PropertiesUtil;
import org.safari.platform.common.utils.StringUtil;
import org.safari.platform.modules.sys.entity.Media;
import org.safari.platform.modules.sys.service.MediaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 媒体文件controller
 * @author Alitalk
 * @date 2017-02-17
 */
@Controller
@RequestMapping("${adminPath}/sys/media")
public class MediaController {
	
	protected Logger LOG = LoggerFactory.getLogger(MediaController.class);
	
	private String upload = PropertiesUtil.getValue("safari.media.upload");
	
	@Autowired
	private MediaService mmediaService;
	
	@RequestMapping("/filehandler/{method}")
	@ResponseBody
	public Object fileHandler(HttpServletRequest request,HttpServletResponse response,
			@PathVariable("method") String method) {
		if (method.equals("query")) {
			return this.findMedias(request);
		} else if (method.equals("down")) {
			return this.downloadMedia(request, response);
		} else if (method.equals("delete")) {
			return this.deleteMedia(request);
		} 
		return "success";
	}
	
    /**
     * 调整为中间表后的读取函数
     * @param request
     * @return
     */
    private List<Media> findMedias(HttpServletRequest request){
        String ids = request.getParameter("ids");
        LOG.info("查看媒体文件ID=" + ids);
        return mmediaService.findMedias(ids);
        
    }
	
    /**
     * 下载
     * @param request
     * @param response
     */
    private String downloadMedia(HttpServletRequest request,HttpServletResponse response){
        String id = request.getParameter("id");
        LOG.info("下载媒体文件ID=" + id);
        Media media = mmediaService.get(id);
        
        InputStream is = null;
		OutputStream os = null;
		if (null != media) {
			try {
				response.setContentType("application/x-msdownload;");
				response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(media.getName(), "utf-8"));
				
				is = new FileInputStream(new File(upload + MediaEnum.getFolder(
						media.getType()) + FileUtil.fileSplit + media.getPath()));
				os = response.getOutputStream();
				
				byte[] buff = new byte[2048];
				int len = 0;
				while ((len = is.read(buff)) > 0) {
					os.write(buff, 0, len);
				}
			} catch (Exception e) {
				LOG.error("下载媒体文件失败" , e);
				e.printStackTrace();
				return "fail";
			} finally {
				try {
					if (os != null){
						os.close();
					}
					if (is != null){
						is.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
        return "success";
    }
    
    /**
     * 删除
     * @param request
     * @return
     */
    private String deleteMedia(HttpServletRequest request){
        String id = request.getParameter("id");
        LOG.info("删除媒体文件ID=" + id);
        if(!StringUtil.isEmpty(id)){
        	try {
        		mmediaService.deleteById(id);
			} catch (Exception e) {
				LOG.error("media>>" , e);
				e.printStackTrace();
				return "fail";
			}
        }
        return "success";
    }
}
