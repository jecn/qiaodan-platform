<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.io.*, java.util.*, org.apache.commons.fileupload.*" %>
<%@ page import="java.lang.reflect.Method"%>
<%@ page import="java.security.Principal"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%@ page import="org.apache.commons.fileupload.disk.*, org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="org.springframework.web.multipart.MultipartHttpServletRequest" %>
<%@ page import="org.springframework.web.multipart.MultipartFile" %>

<%@ page import="org.safari.platform.common.enums.MediaEnum" %>
<%@ page import="org.safari.platform.common.utils.BeanRefUtil"%>
<%@ page import="org.safari.platform.common.utils.FileUtil"%>
<%@ page import="org.safari.platform.common.utils.DateUtil"%>
<%@ page import="org.safari.platform.common.utils.RandomUtil"%>
<%@ page import="org.safari.platform.common.utils.PropertiesUtil"%>
<%@ page import="org.safari.platform.common.utils.SpringContextHolderUtil" %>
<%@ page import="org.safari.platform.common.utils.UUIDUtil"%>
<%@ page import="org.safari.platform.modules.sys.entity.User"%>
<%@ page import="org.safari.platform.modules.sys.entity.Media"%>
<%@ page import="org.safari.platform.modules.sys.mapper.MediaMapper"%>

<%!
private String upload = PropertiesUtil.getValue("safari.media.upload");
public void acceptFile(HttpServletRequest request, HttpServletResponse response){
	/**获取当前登录用户**/
	String currentUser = "";
	String id = "";
   	try {
		Principal principal = request.getUserPrincipal();
		if (null != principal) {
			currentUser = principal.getName();
		} 
   	} catch (Exception e) {
		e.printStackTrace();
	}

	try {
		String entitypath = request.getParameter("entitypath");
		//System.out.println("entitypath:" + entitypath);
		
		String idField = request.getParameter("idfield");
		//System.out.println("idField:" + idField);
		
		String type = request.getParameter("idtype");
		System.out.println("文件类型:" + MediaEnum.getValue(type));
		
		DiskFileItemFactory fac = new DiskFileItemFactory();   //创建一个解析器工厂
		ServletFileUpload servletFileUpload = new ServletFileUpload(fac);  //得到解析器
		servletFileUpload.setHeaderEncoding("UTF-8"); //设置编码方式
		List fileList = servletFileUpload.parseRequest(request);
		Iterator<FileItem> it = fileList.iterator();
		
		//根据反射创建一个实体
		Class<Media> entityClass = (Class<Media>) Class.forName("org.safari.platform.modules.sys.entity.Media");
		Media media = (Media) entityClass.newInstance();
		
		MediaMapper mediaMapper = SpringContextHolderUtil.getBean(MediaMapper.class);
		
		while (it.hasNext()) {
			FileItem item = it.next();
			if (!item.isFormField()) {
				String fileName = item.getName(); //文件名称
				String fileSize = convertFileSize(item.getSize());  //文件大小
				System.out.println("文件名为 :" + fileName + " 文件大小:"+ fileSize);
				
				InputStream is = item.getInputStream();  //获取文件流
				String savePath = saveFile(is, fileName ,type); //文件保存路径
				
				Map<String, String> map = new HashMap<String, String>();
				id = UUIDUtil.generate();
				map.put("id", id);
				map.put("createBy", currentUser);
				map.put("type", type);
				map.put("folder", MediaEnum.getFolder(type));
				map.put("name", fileName);
				map.put("suffix", getSuffix(fileName));
				map.put("length", convertFileSize(item.getSize()));
				map.put("path",savePath);
				
				BeanRefUtil.setFieldValue(media, map);
				
				mediaMapper.insert(media);
			}
		} 
	}catch (Exception ex) {
		id = "";
		ex.printStackTrace();
	}finally{
		try {
			response.getWriter().print(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

/**
 *	获取文件的后缀
*/
public String getSuffix(String fileName) {
	String suffix = "";
	if(null != fileName && !"".equals(fileName)){
		int index = fileName.lastIndexOf(".");
		suffix = fileName.substring(index, fileName.length());
	}
	
	return suffix;
}

/**
 *	计算文件的大小
*/
public String convertFileSize(long filesize) {
	String strUnit = "Bytes";
	String strAfterComma = "";
	int intDivisor = 1;
	if (filesize >= 1024 * 1024) {
		strUnit = "MB";
		intDivisor = 1024 * 1024;
	} else if (filesize >= 1024) {
		strUnit = "KB";
		intDivisor = 1024;
	}
	if (intDivisor == 1){
		return filesize + " " + strUnit;
	}
	strAfterComma = "" + 100 * (filesize % intDivisor) / intDivisor;
	if (strAfterComma == ""){
		strAfterComma = ".0";
	}
	return filesize / intDivisor + "." + strAfterComma + " " + strUnit;
}

/**
 * 保存文件
 * @param is InputStream
 * @param fileName String 
 * @param type String 
 */
public String saveFile(InputStream is, String fileName, String type){
	String savePath = null;
	
	String uniqueFileName = RandomUtil.randomNInt(20); //系统重新分配的新名称
	String suffix = fileName.substring(fileName.lastIndexOf("."));  //媒体文件后缀
	String newFileName = uniqueFileName + suffix; //媒体文件新名称
	
	//将文件存储到磁盘中
    String datePathStr = DateUtil.dateToStr(new Date(),"yyyy/MM/dd"); //生成日期目录
    String fileDirStr = upload + MediaEnum.getFolder(type) + FileUtil.fileSplit + datePathStr;
    File fileDir = new File(fileDirStr);
    
   //判断目录文件是否存在  不存在即创建
    if(!fileDir.exists()) {  
        fileDir.mkdirs();
    }
   
    FileOutputStream fos = null;
    try{
    	fos = new FileOutputStream(fileDirStr + FileUtil.fileSplit + newFileName);
    	byte[] buff = new byte[1024];
    	int len = 0;
        while((len = is.read(buff))>0){
        	 fos.write(buff);
         }
         fos.flush();
         
         savePath = datePathStr + FileUtil.fileSplit + newFileName;
    }catch(Exception e){
    	e.printStackTrace();
    }finally{
    	try{
    		fos.close();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    return savePath;
}
%>

<% 
   acceptFile(request,response);
 %>
