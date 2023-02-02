package com.bbs.controller;

import com.bbs.command.Command;
import com.bbs.command.CommandFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/*")
public class Controller extends HttpServlet {

	protected void handleRequest (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		Command command = CommandFactory.getCommand(request);

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
