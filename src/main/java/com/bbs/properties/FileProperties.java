package com.bbs.properties;

public interface FileProperties {

	/**
	 * 개발 로컬 서버용 절대 경로
	 */
	String tempDirectory = "E:\\coding\\servlet-jsp-bbs\\src\\main\\resources\\file\\temp\\";

	String fileDirectory = "E:\\coding\\servlet-jsp-bbs\\src\\main\\resources\\file\\";

	/**
	 * 우분투 서버용
	 */
//	String tempDirectory = "/var/lib/tomcat9/webapps/file/temp/";
//	String fileDirectory = "/var/lib/tomcat9/webapps/file/";
	int maxPostSize = 1024 * 1024 * 5;
	String encoding = "utf-8";
}
