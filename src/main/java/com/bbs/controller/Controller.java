package com.bbs.controller;

import com.bbs.command.Command;
import com.bbs.command.CommandFactory;
import com.bbs.command.View;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * frontController
 */
@WebServlet("/")
public class Controller extends HttpServlet {

	/**
	 * 요청 경로에 해당하는 Command 객체를 실행하여 해당 페이지 반환
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void handleRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String requestURI = getRequestURI(request);

		Command command = CommandFactory.getCommand(requestURI);

		View view = command.execute(request, response);

		view.resolveView(requestURI);

		view.render(request, response);
	}

	/**
	 * 요청 경로 맨 끝에 슬래쉬가 있을 경우 제거 후 요청 경로 반환
	 *
	 * @param request
	 * @return
	 */
	private String getRequestURI(HttpServletRequest request) {
		String requestURI = request.getRequestURI();

		if (requestURI.length() > 1 && requestURI.endsWith("/")) {
			requestURI = requestURI.substring(0, requestURI.length() - 1);
		}

		return requestURI;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		handleRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		handleRequest(request, response);
	}
}
