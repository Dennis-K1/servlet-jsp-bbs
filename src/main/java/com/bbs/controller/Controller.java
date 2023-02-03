package com.bbs.controller;

import com.bbs.command.Command;
import com.bbs.command.CommandFactory;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
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
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void handleRequest (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		Command command = CommandFactory.getCommand(request.getRequestURI());

		String viewPage = command.execute(request,response);

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);

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
