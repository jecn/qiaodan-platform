package org.safari.platform.common.utils;

import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Title: </p>
 * <p>Description: 通过反射来动态调用get 和 set 方法</p>
 * <p>Company: 深圳市萨法瑞科技有限公司</p>
 * @author Alitalk 
 * @date 2016年5月19日
*/
public class ReflectHelper {
	
	private Logger LOG = LoggerFactory.getLogger(ReflectHelper.class);

	@SuppressWarnings("rawtypes")
	private Class cls;
	/**
	 * 传过来的对象
	 */
	private Object obj;

	/**
	 * 存放get方法
	 */
	private Hashtable<String, Method> getMethods = null;
	/**
	 * 存放set方法
	 */
	private Hashtable<String, Method> setMethods = null;

	/**
	 * 定义构造方法 -- 一般来说是个pojo
	 * @param o
	 *            目标对象
	 */
	public ReflectHelper(Object o) {
		obj = o;
		initMethods();
	}

	/**
	 * 初始化
	 */
	public void initMethods() {
		getMethods = new Hashtable<String, Method>();
		setMethods = new Hashtable<String, Method>();
		cls = obj.getClass();
		Method[] methods = cls.getMethods();
		// 定义正则表达式，从方法中过滤出getter / setter 函数.
		String gs = "get(\\w+)";
		Pattern getM = Pattern.compile(gs);
		String ss = "set(\\w+)";
		Pattern setM = Pattern.compile(ss);
		// 把方法中的"set" 或者 "get" 去掉
		String rapl = "$1";
		String param;
		for (int i = 0; i < methods.length; ++i) {
			Method m = methods[i];
			String methodName = m.getName();
			if (Pattern.matches(gs, methodName)) {
				param = getM.matcher(methodName).replaceAll(rapl).toLowerCase();
				getMethods.put(param, m);
			} else if (Pattern.matches(ss, methodName)) {
				param = setM.matcher(methodName).replaceAll(rapl).toLowerCase();
				setMethods.put(param, m);
			}
		}
	}

	/**
	 * 调用set方法
	 */
	public boolean setMethodValue(String property,Object object) {
		Method m = setMethods.get(property.toLowerCase());
		if (m != null) {
			try {
				// 调用目标类的setter函数
				m.invoke(obj, object);
				return true;
			} catch (Exception ex) {
				LOG.info("invoke getter on " + property + " error: " + ex.toString());
				return false;
			}
		}
		return false;
	}

	/**
	 * 调用set方法
	 */
	public Object getMethodValue(String property) {
		Object value=null;
		Method m = getMethods.get(property.toLowerCase());
		if (m != null) {
			try {
				value=m.invoke(obj, new Object[] {});
			} catch (Exception ex) {
				LOG.info("invoke getter on " + property + " error: " + ex.toString());
				ex.printStackTrace();
			}
		}
		return value;
	}
}