package com.bbs.command.client;

import com.bbs.command.Command;
import com.bbs.command.CommandInformation;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class IndexCommand implements Command {

	@Override
	public CommandInformation execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (session.getAttribute("account") != null) {
			request.setAttribute("account", session.getAttribute("account"));
		}

		return CommandInformation.builder()
			.path(CLIENT_VIEW_PATH + "index.jsp")
			.build();
	}
}
