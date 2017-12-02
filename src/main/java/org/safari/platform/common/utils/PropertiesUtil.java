package org.safari.platform.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

/**
 * <p>Title: 配置文件工具类</p>
 * <p>Company: 深圳市萨法瑞科技有限公司</p>
 * @author Alitalk
 * @date 2015-4-14
 */
public class PropertiesUtil {

	private static Logger LOG = LoggerFactory.getLogger(PropertiesUtil.class);
	
	private static final String ENCODING = "UTF-8";  //编码方式
	 
	public static Properties prop = new LinkedProperties();
	private static boolean initialized = false; // 是否已初始化
	public static final String DEFAULT_FILENAME = "config/application.properties";
	
	
	 public static boolean isInitialized() {
		return initialized;
	}

	public static void setInitialized(boolean initialized) {
		PropertiesUtil.initialized = initialized;
	}

	/**
     * 初始化读取配置文件，读取的文件列表位于classpath下面的application-files.properties<br/>
     * <p/>
     * 多个配置文件会用最后面的覆盖相同属性值
     *
     * @throws IOException 读取属性文件时
     */
    public static void init() {
    	if(!isInitialized()){
    		try {
    			loads(DEFAULT_FILENAME);
    			initialized = true;
    		} catch (IOException e) {
    			LOG.error("配置文件初始化失败",e);
    		}
    	}
    }

    /**
     * 初始化读取配置文件，读取的文件列表位于classpath下面的application-[type]-files.properties<br/>
     * <p/>
     * 多个配置文件会用最后面的覆盖相同属性值
     *
     * @param profile 配置文件类型，application-[profile]-files.properties
     * @throws IOException 读取属性文件时
     */
	public static void init(String profile) {
		if(!isInitialized()){
			try {
				if (StringUtils.isBlank(profile)) {
					init();
				} else {
					loads(profile);
				}
				initialized = true;
			} catch (Exception e) {
				LOG.error("配置文件初始化失败",e);
			}
		}
	}
    
    /**
     * 载入多个properties文件, 相同的属性在最后载入的文件中的值将会覆盖之前的载入.
     * 文件路径使用Spring Resource格式, 文件编码使用UTF-8.
     *
     * @see org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
     */
    public static void loads(String... paths) throws IOException {
        for (String path : paths) {
            if(path.endsWith(".properties")){
            	activeFile(path);
            }
        }
    }
    
    /**
     * 内部处理
     *
     * @param fileName
     * @throws IOException
     */
    private static void activeFile(String fileName) throws IOException {
    	// 剔除classpath路径协议
    	fileName = fileName.replace("classpath*:/", "");
		LOG.info("读取>>>>>" + fileName);
        
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStreamReader fisr = new InputStreamReader(loader.getResourceAsStream
        		(fileName), ENCODING);
        
        // 默认的Properties实现使用HashMap算法，为了保持原有顺序使用有序Map
        prop.load(fisr);
        
        Set<Object> keySet = prop.keySet();
        for (Object key : keySet) {
            LOG.info("【property: {}, value: {}】", key, prop.getProperty(key.toString()));
        }
    	
    }
    
    /**
     * 载入多个properties文件, 相同的属性在最后载入的文件中的值将会覆盖之前的载入.
     * 文件路径使用Spring Resource格式, 文件编码使用UTF-8.
     *
     * @see org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
     */
    public static Properties loadProperties(String... resourcesPaths) throws IOException {
        Properties props = new Properties();
        PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        
        for (String location : resourcesPaths) {
            // 剔除classpath路径协议
            location = location.replace("classpath*:/", "");
            if(location.endsWith(".properties")){
            	LOG.debug("Loading properties file from:" + location);

                InputStream is = null;
                
                try {
                    Resource resource = resourceLoader.getResource(location);
                    is = resource.getInputStream();
                    propertiesPersister.load(props, new InputStreamReader(is, ENCODING));
                } catch (IOException ex) {
                    LOG.info("Could not load properties from classpath:" + location + ": " + ex.getMessage());
                } finally {
                    if (is != null) {
                        is.close();
                    }
                }
        	}
        }
        return props;
    }
    
	/**
	 * 获取未知数据类型属性
	 */
	public static Object get(String key){
		if(!isInitialized()){
			init();
		}
		return get(key, null);
	}

	/**
	 * 获取未知数据类型属性（可指定默认值）
	 */
	public static Object get(String key, Object defaultValue) {
		if(!isInitialized()){
			init();
		}
		
		Object value = defaultValue;
		if (prop.containsKey(key)) {
			value = prop.getProperty(key);
		}
		return value;
	}
	
	/**
	 * 获取整型属性
	 */
	public static int getInt(String key) {
		return getInt(key, 0);
	}

	/**
	 * 获取整型属性（可指定默认值）
	 */
	public static int getInt(String key, int defaultValue) {
		if(!isInitialized()){
			init();
		}
		
		int value = defaultValue;
		if (prop.containsKey(key)) {
			String intStr = prop.getProperty(key);
			if (!StringUtil.isEmpty(intStr)) {
				value = Integer.parseInt(intStr);
			}
		}
		return value;
	}

	/**
	 * 获取浮点型属性
	 */
	public static double getDouble(String key) {
		return getDouble(key, 0);
	}

	/**
	 * 获取浮点型属性（可指定默认值）
	 */
	public static double getDouble(String key, double defaultValue) {
		if(!isInitialized()){
			init();
		}
		
		double value = defaultValue;
		if (prop.containsKey(key)) {
			String doubleStr = prop.getProperty(key);
			if (!StringUtil.isEmpty(doubleStr)) {
				value = Double.parseDouble(doubleStr);
			}
		}
		return value;
	}

	/**
	 * 获取字符型属性
	 */
	public static String getValue(String key) {
		return getValue(key, "");
	}

	/**
	 * 获取字符型属性（可指定默认值）
	 */
	public static String getValue(String key, String defaultValue) {
		if(!isInitialized()){
			init();
		}
		
		String value = defaultValue;
		if (prop.containsKey(key)) {
			value = prop.getProperty(key);
		}
		return value;
	}

	/**
	 * 获取布尔型属性
	 */
	public static boolean getBoolean(String key) {
		return getBoolean(key, false);
	}

	/**
	 * 获取布尔型属性(默认值为 false)
	 */
	public static boolean getBoolean(String key, boolean defaultValue) {
		if(!isInitialized()){
			init();
		}
		boolean value = defaultValue;
		if (prop.containsKey(key)) {
			String booleanStr = prop.getProperty(key);
			if (!StringUtil.isEmpty(booleanStr)) {
				value = Boolean.parseBoolean(booleanStr);
			}
		}
		return value;
	}

	/**
	 * 判断key对应的value是否和期待的一致
	 * 
	 * @param key
	 * @param expectValue
	 * @return
	 */
	public static boolean equalsWith(String key, String expectValue) {
		String value = getValue(key);
		return StringUtil.equals(value, expectValue);
	}
	
	/**
     * 向内存添加属性
     *
     * @param key   键
     * @param value 值
     */
    public static void add(String key, String value) {
    	prop.put(key, value);
        LOG.info("通过方法添加属性到内存：{}，值：{}", key, value);
    }
    
    /**
     * 从内存移除属性
     *
     * @param key   键
     */
    public static void remove(String key) {
    	prop.remove(key);
        LOG.info("通过方法从内存中移除属性：{}", key);
    }
    
    /**
     * 获取键值对Map
     *
     * @return
     */
    public static Map<String, String> getKeyValueMap() {
        Set<String> keys = getKeys();
        Map<String, String> map = new HashMap<String, String>();
        for (String key : keys) {
            map.put(key, getValue(key));
        }
        return map;
    }
    
    /**
     * 获取所有的key
     * @return
     */
    public static Set<String> getKeys() {
        return prop.stringPropertyNames();
    }
}