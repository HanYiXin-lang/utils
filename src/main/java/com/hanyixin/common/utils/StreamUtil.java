package com.hanyixin.common.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName:  StreamUtil   
 * @Description:流处理工具类    
 * @date:   2020年1月3日 上午9:07:57
 */
public class StreamUtil {
	/**
	 * @Title: close   
	 * @Description: 批量关闭流   
	 * @param: @param autoCloseables      
	 * @return: void      
	 * @throws
	 */
	public static void close(AutoCloseable... autoCloseables ) {
		for(AutoCloseable autoCloseable:autoCloseables) {
			try {
				autoCloseable.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	

	
	/**
	 * @Title: readTextFile   
	 * @Description: 以流的方式，读取文本文件内容   
	 * @param: @param file
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String readTextFile(File file) {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
			byte[] b = new byte[1024];
			String str = null;
			while (inputStream.read(b)!=-1) {
				str += new String(b);
			}
			return str;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}finally {
			close(inputStream);
		}
	}
	/**
	 * @Title: getFileContent   
	 * @Description: 根据文件全名读取文件内容   
	 * @param: @param fileFullName
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String readTextFile(String fileFullName) {
		return readTextFile(new File(fileFullName));
	}
	/**
	 * @Title: writeTextFile   
	 * @Description: 把内容写到指定文件   
	 * @param: @param content
	 * @param: @param file
	 * @param: @param append      
	 * @return: void      
	 * @throws
	 */
	public static void writeTextFile(String content,File file,boolean append) {
		BufferedWriter writer = null;
		try {
			//判断写文件的文件夹是否存在
			String parent = file.getParent();
			File parentFile = new File(parent);
			if(!parentFile.exists()) {
				parentFile.mkdirs();
			}
			//写文件
			writer = new BufferedWriter(new FileWriter(file,append));
			writer.write(content);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			close(writer);
		}
	}
	
	public static void writeTextFile(String content,String fileFullName,boolean append) {
		writeTextFile(content,new File(fileFullName), append);
	}
}
