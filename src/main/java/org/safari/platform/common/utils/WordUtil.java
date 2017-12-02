package org.safari.platform.common.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * Word导出工具类
 * @author Alitalk
 * @date 2017-03-13
 */
public class WordUtil {
	
	protected static Logger LOG = LoggerFactory.getLogger(WordUtil.class);
	
	private static String ENCODING = "UTF-8";
	
	private static Configuration configuration = null;
	private static String templatePath = PropertiesUtil.getValue("template.path");
	private static String downloadPath = PropertiesUtil.getValue("download.path");
	
	static{
		configuration = new Configuration();
		configuration.setEncoding(Locale.getDefault(), ENCODING);
	}
	
	/**
	 * 使用ftl文件模板动态生成word内容
	 * @param templateName String 模板名称
	 * @param dataMap 数据内容
	 * @param fileName 文件名称
	 * @return
	 */
	public static boolean exportWord(String templateName,Map<String,String>dataMap,
			String fileName){
		boolean flag = false;
		Writer out = null;
		try {
            //加载模板文件，FTL文件所存在的目录
            configuration.setClassForTemplateLoading(WordUtil.class, templatePath);
            //设置异常处理器
            configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
            
            //加载需要装填的模板
            Template template = configuration.getTemplate(templateName);
           
            File dir = new File(downloadPath + "doc/");
            if(!dir.exists()){
            	dir.mkdir();
            }
            
            File outFile =  new File(dir + "/" + fileName);
            LOG.info(fileName + "文件完整路径>>>" +outFile.getAbsolutePath());
            
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), ENCODING));
            template.process(dataMap, out);
            
            out.close();
            flag = true;
        } catch (IOException e) {
        	LOG.error("Word导出失败>>>" , e);
            e.printStackTrace();
        } catch (TemplateException e) {
        	LOG.error("Word导出失败>>>" , e);
            e.printStackTrace();
        }finally{
        	try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
		
		return flag;
	}
	
	
	public static void main(String[] args) {
		Map<String,String> dataMap = new HashMap<String, String>();
		dataMap.put("titleYear", "2017");
		dataMap.put("deptName", "");
		dataMap.put("deptOfficial", "");
		dataMap.put("leadOfficial", "");
		dataMap.put("preparer", "");
		dataMap.put("phone", "");
		dataMap.put("address", "");
		dataMap.put("code", "");
		dataMap.put("year", "");
		dataMap.put("month", "");
		dataMap.put("day", "");
		dataMap.put("deptQuality", "adm");
		dataMap.put("deptQualityDesc", "合作制单位");
		
		Writer out=null;
		
		try {
			Configuration configuration = new Configuration();
			configuration.setEncoding(Locale.getDefault(), "UTF-8");
			
			//加载模板文件，FTL文件所存在的位置
	        configuration.setClassForTemplateLoading(WordUtil.class, "/template/doc/");
	        //设置异常处理器
	        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
	        
	        //加载需要装填的模板,注意模板类型名字与downloadType要一致
	        Template template=configuration.getTemplate("adm.ftl");
	        
	        File dir = new File("d://" + "doc/" );
	        if(!dir.exists()){
	        	dir.mkdir();
	        }
	       
	        File outFile=new File(dir + "/adm.doc");
	        System.out.println(outFile.getAbsolutePath());
	        
	        out=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"));
	        template.process(dataMap, out);
	        out.close();
		} catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }finally{
        	try {
				out.close();
				System.out.println("执行完毕");
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}
}
