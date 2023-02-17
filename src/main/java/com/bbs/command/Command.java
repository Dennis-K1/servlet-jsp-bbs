package com.bbs.command;

import com.bbs.domain.View;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * request 처리를 위한 Command 인터페이스
 */
public interface Command {

	/**
	 * 커맨드 객체 로직 실행
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	View execute(
		HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException;
}
