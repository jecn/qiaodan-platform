package org.safari.platform.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.safari.platform.modules.sys.utils.I18NUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Title:  系统初始化监听器</p>
 * <p>Description:在系统启动时运行,进行一些初始化工作 </p>
 * <p>Company: 深圳市萨法瑞科技有限公司</p>
 * @author Alitalk 
 * @date 2016年5月20日
*/
public class InitListener implements ServletContextListener {
	
	private Logger LOG = LoggerFactory.getLogger(InitListener.class);
	
	public void contextDestroyed(ServletContextEvent event) {
		
	}
	
	public void contextInitialized(ServletContextEvent event) {
		LOG.info("系统初始化监听器>>>>>>>>>>>>>>>>>>>> BEGIN >>>>>>");
		
		/**
		 *加载多语言内容
		 */
		I18NUtil.initI18N();
		
		LOG.info("系统初始化监听器>>>>>>>>>>>>>>>>>>>> END >>>>>>");
	}

}
