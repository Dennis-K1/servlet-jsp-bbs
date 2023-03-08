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
	 * 아이디 최소 길이
	 */
	private static final int ACCOUNT_MIN = 3;

	/**
	 * 아이디 최대 길이
	 */
	private static final int ACCOUNT_MAX = 9;

	/**
	 * 비밀번호 최소 길이
	 */
	private static final int PASSWORD_MIN = 4;

	/**
	 * 비밀번호 최대 길이
	 */
	private static final int PASSWORD_MAX = 15;

	/**
	 * 비밀번호 형태 제약 (영어 대, 소문자, 숫자, 특수기호 [!,@,#,$,%,^,&,*,?,_,~])
	 */
	private static final String PASSWORD_FORM = "/([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9])/";

	/**
	 * 제목 최소 길이
	 */
	private static final int TITLE_MIN = 5;

	/**
	 * 제목 최대 길이
	 */
	private static final int TITLE_MAX = 49;

	/**
	 * 내용 최소 길이
	 */
	private static final int CONTENT_MIN = 30;

	/**
	 * 내용 최대 길이
	 */
	private static final int CONTENT_MAX = 499;

	/**
	 * 답글(댓글) 최소 길이
	 */
	private static final int REPLY_MIN = 1;

	/**
	 * 답글(댓글) 최대 길이
	 */
	private static final int REPLY_MAX = 99;

	/**
	 * 확장자 제한
	 */
	private static final String[] IMAGE_EXTENSION_LIST = {"jpg", "jpeg", "png"};

	/**
	 * 검색값 날짜 입력 형태 제약 yy-mm-dd
	 */
	private static final String SEARCH_PARAMETER_DATE_FORMAT = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";

	/**
	 * 검색값 카테고리 값 범위 (1 : 전체, 2 : 제목, 3 : 작성자, 4 : 내용)
 	 */
	private static final String ARTICLE_SEARCH_CATEGORY_ID_RANGE = "[1-4]";

	/**
	 *
	 */
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
	public static Boolean isStringPositiveInteger(String parameter) {
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
	 * 아이디 입력값 유효성 검사
	 */
	public static boolean isAccountValid(String account) {
		if (isStringEmpty(account)) {
			return false;
		}
		if (account.length() < ACCOUNT_MIN || account.length() > ACCOUNT_MAX) {
			return false;
		}
		return true;
	}

	/**
	 * 비밀번호 입력값 유효성 검사
	 */
	public static boolean isPasswordValid(String password) {
		if (isStringEmpty(password)) {
			return false;
		}
		if (!password.matches(PASSWORD_FORM)){
			return false;
		}
		if (password.length() < PASSWORD_MIN || password.length() > PASSWORD_MAX) {
			return false;
		}
		return true;
	}

	/**
	 * 제목 입력값 유효성 검사
	 */
	public static boolean isTitleValid(String title) {
		if (isStringEmpty(title)) {
			return false;
		}
		if (title.length() < TITLE_MIN || title.length() > TITLE_MAX) {
			return false;
		}
		return true;
	}

	/**
	 * 내용 입력값 유효성 검사
	 */
	public static boolean isContentValid(String content) {
		if (isStringEmpty(content)) {
			return false;
		}
		if (content.length() < CONTENT_MIN || content.length() > CONTENT_MAX) {
			return false;
		}
		return true;
	}

	/**
	 * 답글(댓글) 입력값 유효성 검사
	 */
	public static boolean isReplyValid(String reply) {
		if (isStringEmpty(reply)) {
			return false;
		}
		if (reply.length() < REPLY_MIN || reply.length() > REPLY_MAX) {
			return false;
		}
		return true;
	}

	/**
	 * 이미지 확장자 유효성 검사
	 */
	public static boolean isImageExtensionValid(String fileExtension) {
		for (String imageExtension : IMAGE_EXTENSION_LIST) {
			if (imageExtension.equals(fileExtension)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 검색 범위 시작일 및 종료일 형태 검사후 유효하지 않을 시 null 반환 (null == 아무 것도 입력하지 않은 값)
	 * @param date
	 * @return
	 */
	public static String getValidatedSearchDate(String date) {
		if (!isStringEmpty(date) && !date.matches(SEARCH_PARAMETER_DATE_FORMAT)) {
			return null;
		}
		return date;
	}

	/**
	 * 게시글 카테고리 번호 유효성 검사후, 유효하지 않을 시 null 반환 (null == 아무 것도 입력하지 않은 값)
	 *
	 * @param searchCategory 게시글 카테고리 번호
	 * @return
	 */
	public static String getValidatedSearchCategory(String searchCategory) {
		if (!isStringEmpty(searchCategory) && !searchCategory.matches(ARTICLE_SEARCH_CATEGORY_ID_RANGE)) {
			return null;
		}
		return searchCategory;
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

	/**
	 * 페이지 번호 유효성 검사
	 * 정수인 경우 대상에 맞게 반환, 아닌 경우 1 반환
	 *
	 * @param pageNumber 사용자 입력 페이지 번호
	 * @return
	 */
	public static int getValidatedPageNumber(String pageNumber) {
		if (Objects.equals(null, pageNumber)){
			return 1;
		}
		if (CommandUtil.isStringPositiveInteger(pageNumber)) {
			return Integer.parseInt(pageNumber);
		}
		return 1;
	}
}
