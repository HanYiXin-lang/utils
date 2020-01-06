package com.hanyixin.common.utils.test;

import java.util.List;

import org.junit.Test;

import com.hanyixin.common.utils.FileUtil;

public class FileTests {
	//测试  
	@Test
	public void readTextFileToList() {
		String fileName = "C:\\Users\\Administrator\\Desktop\\pom.xml";
		List<String> readTextFileToList = FileUtil.readTextFileToList(fileName);
		System.out.println(readTextFileToList);
	}
}
