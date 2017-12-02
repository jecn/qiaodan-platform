package org.safari.platform.common.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池工具
 * @author Alitalk
 * @date 2017-03-30
 */
public class ThreadPoolUtil {

	public static final ExecutorService executor = Executors.newFixedThreadPool(100);
	
}
