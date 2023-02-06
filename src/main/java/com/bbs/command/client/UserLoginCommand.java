package com.bbs.command.client;

import com.bbs.command.Command;
import com.bbs.command.CommandInformation;
import com.bbs.domain.User;
import com.bbs.service.UserService;
import com.bbs.util.CommandUtil;
import java.io.IOException;
import java.rmi.NoSuchObjectException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLoginCommand implements Command {

	@Override
	public CommandInformation execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (CommandUtil.isGETMethod(request)) {

			if (session.getAttribute("account")!= null) {
				return CommandInformation.builder()
					.isRedirect(true)
					.path("/")
					.build();
			}

			return CommandInformation.builder()
				.path(CLIENT_VIEW_PATH + "login.jsp")
				.build();
		}

		User user = User.builder()
			.account(request.getParameter("account"))
			.password(request.getParameter("password"))
			.build();

		UserService userService = new UserService();

		if (!userService.isExistingUser(user)) {
			throw new NoSuchObjectException("asdasd");
		}

		if (!userService.isCorrectPassword(user)) {
			throw new ServletException("비밀번호 틀림");
		}

		session.setAttribute("account", user.getAccount());

		return CommandInformation.builder()
			.isRedirect(true)
			.path("/")
			.build();
	}
}
