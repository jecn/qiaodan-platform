package org.safari.platform.common.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.safari.platform.common.config.Global;
import org.safari.platform.common.utils.LinkedProperties;
import org.safari.platform.common.utils.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * classpath下面的属性配置文件读取初始化类
 * @author Think
 * @date 2017-02-10
 */
public class PropertiesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    protected Logger LOG = LoggerFactory.getLogger(getClass());

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PropertiesServlet() {
        super();
    }

    /**
     * @see Servlet#init(ServletConfig)
     */
    public void init(ServletConfig config) throws ServletException {
    	LOG.info("系统初始化加载配置文件>>>>>>>>>>>>>>>>>>>> BEGIN >>>>>>");
        try {
            String profile = config.getInitParameter("profiles.default");
            if (StringUtils.isNotBlank(profile)) {
            	LOG.info("启用特定的配置文件>>>>>>>>>>>>>" + profile + ">>>>>>");
                PropertiesUtil.init(profile);
            } else {
            	LOG.info("启用默认配置文件>>>>>>>>>>>>>");
            	PropertiesUtil.init();
            }
            setParameterToServerContext(config.getServletContext());
            LOG.info("++++ 初始化[classpath下面的属性配置文件]完成 ++++");
            
            StringBuilder sb = new StringBuilder();
    		sb.append("\r\n====================================================\r\n");
    		sb.append("\r\n                  欢迎使用--" + Global.getConfig("productName")+" \r\n");
    		sb.append("\r\n====================================================\r\n");
    		System.out.println(sb.toString());
        } catch (Exception e) {
        	LOG.error("初始化classpath下的属性文件失败", e);
        }
        LOG.info("系统初始化加载配置文件>>>>>>>>>>>>>>>>>>>> END >>>>>>");
    }

    /**
     * 绑定参数到ServletContext
     *
     * @param servletContext
     */
    private void setParameterToServerContext(ServletContext servletContext) {
        servletContext.setAttribute("prop", PropertiesUtil.getKeyValueMap());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = StringUtils.defaultString(req.getParameter("action"));
        resp.setContentType("text/plain;charset=UTF-8");
        if ("reload".equals(action)) { // 重载
            try {
                String profile = StringUtils.defaultString(req.getParameter("profile"), PropertiesUtil.DEFAULT_FILENAME);
                if (StringUtils.isNotBlank(profile)) {
                	LOG.info("重载配置，使用特定Profile=" + profile);
                }
                PropertiesUtil.init(profile);

                setParameterToServerContext(req.getSession().getServletContext());
                LOG.info("++++ 已完成属性配置文件重载任务 ++++，{IP={}}", req.getRemoteAddr());
                resp.getWriter().print("<b>属性文件重载成功！</b><br/>");
                writeProperties(resp);
            } catch (IOException e) {
            	LOG.error("重载属性文件失败", e);
            }
        } else if ("getprop".equals(action)) { // 获取属性
            String key = StringUtils.defaultString(req.getParameter("key"));
            resp.getWriter().print(ObjectUtils.toString(PropertiesUtil.getValue(key)));
        } else if ("list-all".equals(action)) { // 获取全部属性
            writeProperties(resp);
        } else if ("list-split".equals(action)) { // 分文件获取全部属性
            writePropertiesBySplit(resp);
        } else if ("files".equals(action)) {
            writeActiveFiles(resp);
        } else if ("save".equals(action)) {
            String parameterName = StringUtils.defaultString(req.getParameter("parameterName"));
            String parameterValue = StringUtils.defaultString(req.getParameter("parameterValue"));
            saveParameter(parameterName, parameterValue, resp);
        } else if ("delete".equals(action)) {
            String parameterKey = StringUtils.defaultString(req.getParameter("parameterKey"));
            deleteParameter(parameterKey, resp);
        } else {
            writeNav(req, resp);
        }
    }

    /**
     * 新增/修改本地配置文件的属性
     *
     * @param parameterName
     * @param parameterValue
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void saveParameter(String parameterName, String parameterValue, HttpServletResponse resp) throws ServletException, IOException {
        InputStream inputStream = null;
        InputStream cInputStream = null;
        OutputStream out = null;
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            inputStream = loader.getResourceAsStream("application-files.properties");
            Properties props = new Properties();
            props.load(inputStream);
            String cFilePath = props.getProperty("C").split(":")[1];

            File file = new File(cFilePath);
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            Properties cProps = new Properties();
            cInputStream = new FileInputStream(file);
            cProps.load(cInputStream);
            out = new FileOutputStream(cFilePath);
            cProps.setProperty(parameterName, parameterValue);
            cProps.store(out, "Update:'" + parameterName + "' value:" + parameterValue);
            out.flush();
            out.close();
            cInputStream.close();
            inputStream.close();
            PropertiesUtil.init();
            resp.getWriter().print("success");
        } catch (Exception e) {
        	LOG.error("新增/修改属性:", e);
            resp.getWriter().print("error:" + e.getMessage());
        } finally {
            if (null != inputStream) {
                inputStream.close();
            }
            if (null != cInputStream) {
                cInputStream.close();
            }
            if (null != out) {
                out.close();
            }
        }
    }

    /**
     * 删除本地配置文件的属性
     *
     * @param parameterKey
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void deleteParameter(String parameterKey, HttpServletResponse resp) throws ServletException, IOException {
        InputStream inputStream = null;
        InputStream cInputStream = null;
        OutputStream out = null;
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            inputStream = loader.getResourceAsStream("application.properties");
            Properties props = new Properties();
            props.load(inputStream);
            String cFilePath = props.getProperty("C").split(":")[1];

            File file = new File(cFilePath);
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            Properties cProps = new Properties();
            cInputStream = new FileInputStream(file);
            cProps.load(cInputStream);
            cProps.remove(parameterKey);
            out = new FileOutputStream(cFilePath);
            cProps.store(out, "delete:'" + parameterKey);
            out.flush();
            out.close();
            cInputStream.close();
            inputStream.close();
            PropertiesUtil.init();
            resp.getWriter().print("success");
        } catch (Exception e) {
        	LOG.error("删除本地配置文件的属性:", e);
            resp.getWriter().print("error:" + e.getMessage());
        } finally {
            if (null != inputStream) {
                inputStream.close();
            }
            if (null != cInputStream) {
                cInputStream.close();
            }
            if (null != out) {
                out.close();
            }
        }
    }

    /**
     * 输出属性以及值列表到页面
     *
     * @param resp
     * @throws IOException
     */
    protected void writeProperties(HttpServletResponse resp) throws IOException {
        Set<String> keys = PropertiesUtil.getKeys();
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            sb.append(key + "<span style='color:red;font-weight:bold;'>=</span>" + PropertiesUtil.getValue(key) + "<br/>");
        }
        resp.setContentType("text/html");
        resp.getWriter().print("<html><body>" + sb.toString() + "</body></html>");
    }

    /**
     * 分文件获取全部属性
     *
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void writePropertiesBySplit(HttpServletResponse resp) throws ServletException, IOException {
        InputStream inputStream = null;
        InputStream cInputStream = null;
        StringBuilder sb = new StringBuilder();
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            inputStream = loader.getResourceAsStream("application-files.properties");
            Properties props = new LinkedProperties();
            props.load(inputStream);
            Set<Object> fileKeySet = props.keySet();
            for (Object obj : fileKeySet) {
            	LOG.debug("读取文件:key={}, value={}", obj, props.getProperty(obj.toString()));
                sb.append("<span style='color:red;font-weight:bold;'>" + props.getProperty(obj.toString()) + "</span><br/>");
                if (props.getProperty(obj.toString()).startsWith("file:")) {
                    File file = new File(props.getProperty(obj.toString()).split(":")[1]);
                    if (file.getParentFile() != null && !file.getParentFile().exists()) {
                        continue;
                    }
                    if (!file.exists()) {
                        continue;
                    }
                    cInputStream = new FileInputStream(file);
                } else {
                    cInputStream = loader.getResourceAsStream(props.getProperty(obj.toString()));
                }

                Properties cProps = new LinkedProperties();
                cProps.load(cInputStream);
                Set<Object> cFileKeySet = cProps.keySet();
                for (Object cObj : cFileKeySet) {
                    sb.append(cObj.toString() + "<span style='color:red;font-weight:bold;'>=</span>" + cProps.get(cObj.toString()) + "<br/>");
                }
                cInputStream.close();
            }
            inputStream.close();
            resp.setContentType("text/html");
            resp.getWriter().print("<html><body>" + sb.toString() + "</body></html>");
        } catch (Exception e) {
        	LOG.error("删除本地配置文件的属性:", e);
            resp.getWriter().print("error:" + e.getMessage());
        } finally {
            if (null != inputStream) {
                inputStream.close();
            }
            if (null != cInputStream) {
                cInputStream.close();
            }
        }
    }

    /**
     * 输出启用的配置文件列表到页面
     *
     * @param resp
     * @throws IOException
     */
    protected void writeActiveFiles(HttpServletResponse resp) throws IOException {
        Properties activePropertyFiles = PropertiesUtil.loadProperties();
        Enumeration<Object> keys = activePropertyFiles.keys();
        StringBuilder sb = new StringBuilder();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement().toString();
            sb.append(key + "<span style='color:red;font-weight:bold;'>=</span>" + activePropertyFiles.get(key) + "<br/>");
        }
        resp.setContentType("text/html");
        resp.getWriter().print("<html><body><h4>依次读取以下配置文件（Profile=" + PropertiesUtil.DEFAULT_FILENAME+ "）：</h4>" + sb.toString() + "</body></html>");
    }

    protected void writeNav(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        String contextPath = req.getContextPath();
        PrintWriter pw = resp.getWriter();
        String elementformat = "<li><a href='" + contextPath + "/servlet/properties?action=%1s' target='_blank'>%2s</a></li>";
        pw.println("<ul>");
        pw.println(String.format(elementformat, "files", "属性文件列表"));
        pw.println(String.format(elementformat, "list-all", "属性列表（全部）"));
        pw.println(String.format(elementformat, "list-split", "属性列表（分文件）"));
        pw.println(String.format(elementformat, "reload", "重新加载"));
        pw.println(String.format(elementformat, "getprop&key=sample", "获取属性"));
        pw.println("</ul>");
    }
}