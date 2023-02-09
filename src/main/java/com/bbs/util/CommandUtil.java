package com.bbs.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 커맨드, 요청, 응답에 공통적으로 사용되는 util 모음
 */
public class CommandUtil {

	/**
	 * 주어진 문자열이 부호 없는 정수인지 검증
	 *
	 * @param parameter 문자열 입력
	 * @return Boolean
	 */
	public static Boolean isPositiveInteger(String parameter) {
		if (parameter == null) {
			return false;
		}
		return parameter.matches("^[1-9]\\d*");
	}

	/**
	 * 사용자가 로그인된 상태인지 확인
	 * @param session 세션
	 * @param sessionKey 로그인 시 세션 저장 키
	 * @return boolean
	 */
	public static boolean isUserLoggedIn(HttpSession session, String sessionKey){
		String loginSessionValue = (String) session.getAttribute(sessionKey);
		if (loginSessionValue != null) {
			return true;
		}
		return false;
	}

	/**
	 * GET 요청 여부 확인
	 * 로직 없이 단순 form 페이지 반환을 위한 GET 요청 확인 util
	 *
	 * @param request
	 * @return
	 */
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
