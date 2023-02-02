package com.bbs.controller;

import com.bbs.command.Command;
import com.bbs.command.CommandFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 어드민 페이지 Controller
 */
@WebServlet("/admin/*")
public class AdminController extends HttpServlet {

	/**
	 * CommandFactory 를 통해 요청된 페이지 분석하여 반환
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void handleRequest (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		Command command = CommandFactory.getCommand(request);

		String viewPage = command.execute(request,response);

//		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/index.jsp");
//		dispatcher.forward(request, response);

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
