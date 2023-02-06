package com.bbs.controller;

import com.bbs.command.Command;
import com.bbs.command.CommandFactory;
import com.bbs.command.CommandInformation;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void handleRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String commandKey = getCommandKey(request);

		Command command = CommandFactory.getCommand(commandKey);

		CommandInformation commandInformation = command.execute(request, response);

		if (commandInformation.isRedirect()) {
			response.sendRedirect(commandInformation.getPath());
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(commandInformation.getPath());
			dispatcher.forward(request, response);
		}

	}

	private String getCommandKey(HttpServletRequest request) {
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
