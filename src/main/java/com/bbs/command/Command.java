package com.bbs.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * request 처리를 위한 Command 인터페이스
 */
public interface Command {

	/**
	 * 어드민 페이지 경로
	 */
	String ADMIN_VIEW_PATH = "/WEB-INF/views/admin/";

	/**
	 * 클라이언트 페이지 경로
	 */
	String CLIENT_VIEW_PATH = "/WEB-INF/views/client/";

	/**
	 * 커맨드 객체 로직 실행
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	String execute(
		HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException;
}
