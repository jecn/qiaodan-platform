package org.safari.platform.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Title:文件工具类 </p>
 * <p>Description: </p>
 * <p>Company: 深圳市萨法瑞科技有限公司</p>
 * @author Alitalk 
 * @date 2015-4-14
 */
public class FileUtil {
	
	protected static Logger LOG = LoggerFactory.getLogger(FileUtil.class);
	
	public static String fileSplit = "/";  //分隔符

	/**
	 * 读取文件
	 * @date 2015-4-14
	 * @param inputStream InputStream 文件流
	 * @param url String 路径
	 * @param newFileName String 文件名'
	 */
	public static void readFile(InputStream inputStream, String url,
			String newFileName) {
		byte[] b = new byte[2048];
		try {
			File file = new File(url + newFileName);
			FileOutputStream out = new FileOutputStream(file);
			int len = 0;
			do {
				len = inputStream.read(b);
				if (len == -1){
					break;
				}
				out.write(b, 0, len);
			} while (len != -1);

			file.exists();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将文件转成二进制流串
	 * @date 2015-7-13
	 * @param filePath String 文件路径
	 * @return byte[]
	 */
	public static byte[] saveFileByByte(String filePath){
		 InputStream is = null;
		 ByteArrayOutputStream bos = null;
		 byte fileByte[] = new byte[2048];
		 
		 try {
			 File file = new File(filePath);
			 is = new FileInputStream(file);  //写入文件
			 bos = new ByteArrayOutputStream();
			 
			 int len = -1;
			 while((len = is.read(fileByte)) != -1){
				 bos.write(fileByte,0,len);
			 }
			 fileByte = bos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				bos.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileByte;
	}
	
	/**
	 * 将二进制流串转换成文件
	 * @date 2015-7-14
	 * @param fileByte byte[] 二进制流
	 * @param filePath String 文件存放路径
	 * @return int 1 成功 0 失败
	 */
	public static int getFileByByte(byte[] fileByte,
			String filePath){
		int state = 1;
		if(null == fileByte){
			state = 0;
			return state;
		}
		
		InputStream is = null;
		FileOutputStream fos = null;
		byte[] buf = new byte[2048];
		try {
				
			// 将生成的图片格式字符串 fileByte，还原成图片显示 
			is = new ByteArrayInputStream(fileByte);
			
			File file = new File(filePath);
			fos = new FileOutputStream(file);  //读文件
			
			//读操作
			int nRead = 0;
			while ((nRead = is.read(buf)) != -1) {
				fos.write(buf, 0, nRead);
			}
			fos.flush();
		} catch (Exception e) {
			state = 0;
			e.printStackTrace();
		} finally {
			try {
				is.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return state;
	}
	
	
	/**
	* 二进制转字符串
	* @param b byte[]
	* @return String
	*/
	public static String byte2hex(byte[] b){
	
		StringBuffer sb = new StringBuffer();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0XFF);
			if (stmp.length() == 1) {
				sb.append("0" + stmp);
			} else {
				sb.append(stmp);
			}

		}
		return sb.toString();
	}

	/**
	* 字符串转二进制
	* @param str 要转换的字符串
	* @return byte[] 转换后的二进制数组
	*/
	public static byte[] hex2byte(String str) {
		if (StringUtil.isEmpty(str)){
			return null;
		}
		str = str.trim();
		int len = str.length();
		if (len == 0 || len % 2 == 1){
			return null;
		}
		byte[] b = new byte[len / 2];
		try {
			for (int i = 0; i < str.length(); i += 2) {
				b[i / 2] = (byte) Integer
						.decode("0X" + str.substring(i, i + 2)).intValue();
			}
			return b;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	/**
	 * 根据路径创建文件夹
	 * @date 2015-11-1
	 * @param filePath Stirng 文件夹名称
	 * @return boolean
	 */
	public static boolean createFloder(String filePath){
		boolean flag = true;
		File file = new File(filePath);
		//判断文件夹是否存在 不存在即创建
		if (!file .exists()  && !file .isDirectory()){
			flag = file.mkdirs();
		}   
		return flag;
	}

	/**
	 * 创建文件
	 * @param dir String 文件存在目录
     * @param name String 名称
     * @param suffix String 后缀
	 * @return File
	 */
	public static File createFile(String dir, String name, String suffix) {
		File file = new File(dir);
		
		if(!file.exists()){
			file.mkdirs();
		}
		String fileName = name.concat(".").concat(suffix);
		String path = file.getPath().concat(File.separator).concat(fileName);

		file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}
	
	/**
	 * 修改文件名称
	 * @param dirFile String 文件开始存在目录
     * @param oldName String 文件原始名称
     * @param newName String 文件新名称
	 * @return File
	 */
	public static File renameFile(String dirFile, String oldName, String newName) {
		File oldFile = new File(dirFile + fileSplit + oldName);
		
		if(!oldFile.exists()){
			LOG.info("源文件不存在");
			return null;
		}
		
		File newFile = new File(dirFile + newName);
		oldFile.renameTo(newFile); 
		return newFile;
	}
	
	/**
	 * 获取文件大小
	 * @param file
	 * @return long
	 * @throws Exception
	 */
	public static long fileSizes(File file) throws Exception {
		long size = 0;
		if (file.exists()) {
			size = file.length();
		}
		return size;
	}
	
	/**
	 * 获取转换文件大小
	 * @param fileSize long 文件大小
	 * @return String
	 */
	public static String formatFileSize(long fileSize) {
		DecimalFormat df = new DecimalFormat("#.00");
		String fileLength= "";
		if (fileSize < 1024) {
			fileLength = df.format((double) fileSize) + "B";
		} else if (fileSize < 1048576) {
			fileLength = df.format((double) fileSize / 1024) + "KB";
		} else if (fileSize < 1073741824) {
			fileLength = df.format((double) fileSize / 1048576) + "MB";
		} else {
			fileLength = df.format((double) fileSize / 1073741824) + "GB";
		}
		return fileLength;
	}
	
}
