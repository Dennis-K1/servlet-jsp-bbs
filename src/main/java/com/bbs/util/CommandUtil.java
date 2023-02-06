package com.bbs.util;

import javax.servlet.http.HttpServletRequest;

public class CommandUtil {

	public static boolean isGETMethod(HttpServletRequest request) {
		return request.getMethod().equals("GET");
	}


	/**
	 * 경로명 시작이 어드민 페이지에 해당하는 "admin"인지 확인
	 * @param requestURI 요청 경로
	 * @return admin 페이지 요청일 시 true
	 */
	public static boolean isAdminRequest(String requestURI) {
		String[] dividedPaths = requestURI.split("/");
		if (dividedPaths.length > 1 && dividedPaths[1].equals("admin")) {
			return true;
		}
		return false;
	}

}
