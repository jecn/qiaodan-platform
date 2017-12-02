package org.safari.platform.common.mybatis;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field; 
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.Configuration;
import org.safari.platform.common.utils.PropertiesUtil;
import org.safari.platform.common.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedIOException;
import org.springframework.core.io.Resource;

import com.google.common.collect.Sets;

/**
 * 自动刷新MyBatis的mapper文件
 * 实现MyBatis Mapper XML文件增量动态刷新，自动加载，热加载，热部署
 * @author Alitalk
 * @date 2017-02-18
 */
public class MapperRefresh implements java.lang.Runnable {
	
	public static Logger LOG = LoggerFactory.getLogger(MapperRefresh.class);  
	
    private static boolean enabled;         // 是否启用Mapper刷新线程功能  
    private static boolean refresh;         // 刷新启用后，是否启动了刷新线程  
      
    private Set<String> location;         // Mapper实际资源路径  
      
    private Resource[] mapperLocations;     // Mapper资源路径  
    private Configuration configuration;        // MyBatis配置对象  
      
    private Long beforeTime = 0L;           // 上一次刷新时间  
    private static int delaySeconds;        // 延迟刷新秒数  
    private static int sleepSeconds;        // 休眠时间  
    private static String mapperPath;      // xml文件夹匹配字符串，需要根据需要修改 
    
    static {  
    	
        enabled = PropertiesUtil.getBoolean("enabled");  
        delaySeconds = PropertiesUtil.getInt("delaySeconds");  
        sleepSeconds = PropertiesUtil.getInt("sleepSeconds");  
        mapperPath = PropertiesUtil.getValue("mapperPath");  
  
        delaySeconds = delaySeconds == 0 ? 50 : delaySeconds;  
        sleepSeconds = sleepSeconds == 0 ? 3 : sleepSeconds;  
        mapperPath = StringUtil.isBlank(mapperPath) ? "sqlMap" : mapperPath;  
  
        LOG.info("[enabled] " + enabled);  
        LOG.info("[delaySeconds] " + delaySeconds);  
        LOG.info("[sleepSeconds] " + sleepSeconds);  
        LOG.info("[mapperPath] " + mapperPath);  
    }  
    
    public static boolean isRefresh() {  
        return refresh;  
    }  
  
    public MapperRefresh(Resource[] mapperLocations, Configuration configuration) {  
        this.mapperLocations = mapperLocations;  
        this.configuration = configuration;  
    }  

	@Override
	public void run() {
		beforeTime = System.currentTimeMillis();  
		  
        LOG.info("[location] " + location);  
        LOG.info("[configuration] " + configuration);  
  
        if (enabled) {  
            // 启动刷新线程  
            final MapperRefresh runnable = this;  
            new Thread(new java.lang.Runnable() {  
                @Override  
                public void run() {  
                      
                    if (location == null){  
                        location = Sets.newHashSet();  
                        LOG.info("MapperLocation's length:" + mapperLocations.length);  
                        for (Resource mapperLocation : mapperLocations) {  
                            String s = mapperLocation.toString().replaceAll("\\\\", "/");  
                            s = s.substring("file [".length(), s.lastIndexOf(mapperPath) + mapperPath.length());  
                            if (!location.contains(s)) {  
                                location.add(s);  
                                LOG.info("Location:" + s);  
                            }  
                        }  
                        LOG.info("Locarion's size:" + location.size());  
                    }  
  
                    try {  
                        Thread.sleep(delaySeconds * 1000);  
                    } catch (InterruptedException e2) {  
                        e2.printStackTrace();  
                    }  
                    refresh = true;  
                    LOG.info("========= 刷新MyBatis的Mapper文件 =========");  
                    
                    while (true) {  
                        try {  
                            for (String s : location) {  
                                runnable.refresh(s, beforeTime);  
                            }  
                        } catch (Exception e1) {  
                            e1.printStackTrace();  
                        }  
                        try {  
                            Thread.sleep(sleepSeconds * 1000);  
                        } catch (InterruptedException e) {  
                            e.printStackTrace();  
                        }  
  
                    }  
                }  
            }, "MyBatis-Mapper-Refresh").start();  
        }  
	}

	/** 
     * 执行刷新 
     * @param filePath 刷新目录 
     * @param beforeTime 上次刷新时间 
     * @throws NestedIOException 解析异常 
     * @throws FileNotFoundException 文件未找到 
     */  
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private void refresh(String filePath, Long beforeTime) throws Exception {  
        // 本次刷新时间  
        Long refrehTime = System.currentTimeMillis();  
  
        // 获取需要刷新的Mapper文件列表  
        List<File> fileList = this.getRefreshFile(new File(filePath), beforeTime);  
        for (int i = 0; i < fileList.size(); i++) {  
        	File file = fileList.get(i);
        	if(!file.exists()){
        		LOG.info("本次刷新的" + file.getName() + "不存在");
        		continue;
        	}
        	LOG.info("本次刷新的Mapper文件为：" + file.getName());
        	
            InputStream inputStream = new FileInputStream(file);  
            String resource = fileList.get(i).getAbsolutePath();  
            try {  
                // 清理原有资源，更新为自己的StrictMap方便，增量重新加载  
                String[] mapFieldNames = new String[]{  
                    "mappedStatements", "caches",  
                    "resultMaps", "parameterMaps",  
                    "keyGenerators", "sqlFragments"  
                };  
                for (String fieldName : mapFieldNames){  
                    Field field = configuration.getClass().getDeclaredField(fieldName);  
                    field.setAccessible(true);  
                    Map map = ((Map)field.get(configuration));  
                    if (!(map instanceof StrictMap)){  
                        Map newMap = new StrictMap(StringUtil.capitalize(fieldName) + "collection");  
                        for (Object key : map.keySet()){  
                            try {  
                                newMap.put(key, map.get(key));  
                            }catch(IllegalArgumentException ex){  
                                newMap.put(key, ex.getMessage());  
                            }  
                        }  
                        field.set(configuration, newMap);  
                    }  
                }  
                  
                // 清理已加载的资源标识，方便让它重新加载。  
                Field loadedResourcesField = configuration.getClass().getDeclaredField("loadedResources");  
                loadedResourcesField.setAccessible(true);  
                Set loadedResourcesSet = ((Set)loadedResourcesField.get(configuration));  
                loadedResourcesSet.remove(resource);  
                  
                //重新编译加载资源文件。  
                XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(inputStream, configuration,   
                        resource, configuration.getSqlFragments());  
                xmlMapperBuilder.parse();  
            } catch (Exception e) {  
                throw new NestedIOException("Failed to parse mapping resource: '" + resource + "'", e);  
            } finally {  
                ErrorContext.instance().reset();  
            }  
            LOG.info("Refresh file: " + mapperPath + StringUtil.substringAfterLast(fileList.get(i).getAbsolutePath(), mapperPath));  
            LOG.info(file.getName() + "文件刷新结束");
        }  
        // 如果刷新了文件，则修改刷新时间，否则不修改  
        if (fileList.size() > 0) {  
            this.beforeTime = refrehTime;  
        }  
    }  
      
    /** 
     * 获取需要刷新的文件列表 
     * @param dir 目录 
     * @param beforeTime 上次刷新时间 
     * @return 刷新文件列表 
     */  
    private List<File> getRefreshFile(File dir, Long beforeTime) {  
        List<File> fileList = new ArrayList<File>();  
  
        File[] files = dir.listFiles();  
        if (files != null) {  
            for (int i = 0; i < files.length; i++) {  
                File file = files[i];  
                if (file.isDirectory()) {  
                    fileList.addAll(this.getRefreshFile(file, beforeTime));  
                } else if (file.isFile()) {  
                    if (this.checkFile(file, beforeTime)) {  
                        fileList.add(file);  
                    }  
                } else {  
                    LOG.error("Error file." + file.getName());  
                }  
            }  
        }  
        return fileList;  
    }  
  
    /** 
     * 判断文件是否需要刷新 
     * @param file 文件 
     * @param beforeTime 上次刷新时间 
     * @return 需要刷新返回true，否则返回false 
     */  
    private boolean checkFile(File file, Long beforeTime) {  
        if (file.lastModified() > beforeTime) {  
            return true;  
        }  
        return false;  
    }  
    
  
}
