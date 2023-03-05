package com.bbs.controller;

import com.bbs.command.Command;
import com.bbs.command.CommandFactory;
import com.bbs.domain.View;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 모든 요청이 시작되는 frontController
 *
 * 1. request 경로 정보를 이용하여 해당하는 Command 객체를 CommandFactory 에서 생성
 * 2. Command 객체가 요청 정보를 이용하여 로직 실행 후 해당하는 View 객체 생성 반환
 * 3. View.resolverPath 를 통해 redirect 나 forward 에 맞는 경로 resolve,
 * 4. View.render 를 통해 해당 JSP (프론트) 클라이언트 반환
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

		request.setCharacterEncoding("UTF-8");

		String requestURI = getRequestURI(request);

		Command command = CommandFactory.getCommand(requestURI);

		View view = command.execute(request, response);

		view.resolvePath(requestURI);

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
