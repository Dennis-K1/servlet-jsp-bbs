package com.bbs.util;

import com.bbs.command.AdminCommands;
import com.oreilly.servlet.MultipartRequest;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 커맨드, 요청, 응답에 공통적으로 사용되는 util 모음
 */
public class CommandUtil {

	/**
	 * 세션 저장된 사용자 아이디 조회
	 * @param request 요청 객체
	 * @param loginSessionKey 클라이언트 / 어드민 로그인 세션 키
	 * @return 사용자 아이디
	 */
	public static String getUserAccountFromSession(HttpServletRequest request, String loginSessionKey) {
		HttpSession session = request.getSession();
		return (String) session.getAttribute(loginSessionKey);
	}

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

	/**
	 * 요청 경로에 맞는 게시판 pk 반환
	 *
	 * @param request 요청 객체
	 * @return
	 */
	public static Long getBoardIdByRequest(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		String[] requestPaths = requestURI.split("/");
		String requestPath = "/" + requestPaths[1] + "/" + requestPaths[2];
		return AdminCommands.getBoardIdMap().get(requestPath);
	}

	/**
	 * 파일 첨부 여부 확인
	 *
	 * @param multipartRequest
	 * @return
	 */
	public static Boolean isFileUploaded(MultipartRequest multipartRequest) {
		if (Objects.equals(null, multipartRequest.getFilesystemName("file"))) {
			return false;
		}
		return true;
	}

	/**
	 * 빈 문자열인지 확인
	 *
	 * @param parameter
	 * @return
	 */
	public static Boolean isStringEmpty(String parameter) {
		if (Objects.equals(null, parameter) || "".equals(parameter)) {
			return true;
		}
		return false;
	}

	/**
	 * 게시판 번호에 해당하는 경로 반환 일치하지 않는 경우 인덱스 경로 반환
	 *
	 * @param boardId 게시판 번호
	 * @return
	 */
	public static String getPathByBoardId(Long boardId) {
		Map<String, Long> boardIdMap = AdminCommands.getBoardIdMap();
		for (Entry<String, Long> entry: boardIdMap.entrySet()) {
			if (entry.getValue() == boardId){
				return entry.getKey();
			}
		}
		return AdminCommands.INDEX.getPath();
	}
}
