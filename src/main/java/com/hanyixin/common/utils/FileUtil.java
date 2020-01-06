package com.hanyixin.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:  FileUtil   
 * @Description: 文件工具类  
 * @date:   2020年1月3日 上午9:06:40
 */
public class FileUtil {
	/**
	 * @Title: getExtName   
	 * @Description: 文件扩展名
	 * @param: @param str
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String getExtName(String str) {
		if(StringUtil.isNull(str)) {
			return null;
		}
		if(!str.contains(".")) {
			throw new RuntimeException("无法获取文件扩展名");
		}
		return str.substring(str.indexOf("."));
	}
	/**
	 * @Title: delete   
	 * @Description: 递归删除文件   
	 * @param: @param file      
	 * @return: void      
	 * @throws
	 */
	public static void delete(File file) {
		/** 获取文件列表 **/
		File[] listFiles = file.listFiles();
		for(File theFile : listFiles) {
			/** 如果是文件夹，递归删除 **/
			if(theFile.isDirectory()) {
				delete(theFile);
				/** 删除空文件夹 **/
				theFile.delete();
			}else {
				/** 如果是文件，直接删除 **/
				theFile.delete();
			}
			
		}
	}
	/**
	 * @Title: delete   
	 * @Description: 递归删除文件   
	 * @param: @param pathname      
	 * @return: void      
	 * @throws
	 */
	public static void delete(String pathname) {
		delete(new File(pathname));
	}
	/**
	 * 获取系统当前用户目录
	 * @return
	 */
	public static String getSystemUserHome() {
		return System.getProperty("user.home");
	}
	/**
	 * @Title: getSystemTempDirectory   
	 * @Description: 操作系统临时目录
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String getSystemTempDirectory() {
		return System.getProperty("java.io.tmpdir");
	}
	/**
	 * @Title: getFileSize   
	 * @Description: 获得文件大小
	 * 返回文件以指定单位大小表示
	 * File a.txt=2k  
	 * @param: @param file
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String getFileSize(File file) {
		long length = file.length();
		double len = length/1024.0;
		return String.format("%.2f",len)+"kb";
	}
	/**
	 * @Title: readTextFile   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param file
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String readTextFile(File file) {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			do {
				String readLine = br.readLine();
				sb.append(readLine);
				sb.append("\r\n");
			}while(br.read()!=-1);
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			StreamUtil.close(br);
		}
		return sb.toString();
	}
	/**
	 * @Title: readTextFileToList   
	 * @Description: 读取文本文件到list   
	 * @param: @param file
	 * @param: @return      
	 * @return: List<String>      
	 * @throws
	 */
	public static List<String> readTextFileToList(File file) {
		List<String> list = new ArrayList<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			do {
				String readLine = br.readLine();
				list.add(readLine);
			}while(br.read()!=-1);
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			StreamUtil.close(br);
		}
		return list;
	}
	/**
	 * @Title: readTextFileToList   
	 * @Description: 读取文本文件到list   
	 * @param: @param pathname
	 * @param: @return      
	 * @return: List<String>      
	 * @throws
	 */
	public static List<String> readTextFileToList(String pathname){
		return readTextFileToList(new File(pathname));
	}
	
	public static void main(String[] args) {
		String systemUserHome = getSystemTempDirectory();
		System.out.println(systemUserHome);
		Object len;
		String format = String.format("String.format测试：%s短短的,%s",1,2);
		File file = new File("C:\\Users\\Administrator\\Desktop\\pom.xml");
		System.out.println(readTextFileToList(file));
	}
}
