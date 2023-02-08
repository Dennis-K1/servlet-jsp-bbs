package com.bbs.command.client;

import com.bbs.command.Command;
import com.bbs.command.View;
import com.bbs.domain.User;
import com.bbs.service.UserService;
import com.bbs.util.CommandUtil;
import com.bbs.util.SessionKeys;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLoginCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (CommandUtil.isGETMethod(request)) {
			if (session.getAttribute(SessionKeys.LOGIN_CLIENT) != null) {
				return View.builder()
					.isRedirect(true)
					.path("/")
					.build();
			}

			return View.builder()
				.path("/loginForm")
				.build();
		}

		UserService userService = new UserService();
		User user = User.builder()
			.account(request.getParameter("account"))
			.password(request.getParameter("password"))
			.build();

		if (userService.login(user) == null) {
			return View.builder()
				.isRedirect(true)
				.isValidationError(true)
				.path("/login")
				.build();
		}

		session.setAttribute(SessionKeys.LOGIN_CLIENT, user.getAccount());

		return View.builder()
			.isRedirect(true)
			.path("/")
			.build();
	}
}
