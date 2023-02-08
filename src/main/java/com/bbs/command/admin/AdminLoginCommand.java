package com.bbs.command.admin;

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

public class AdminLoginCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (CommandUtil.isGETMethod(request)) {
			if (session.getAttribute(SessionKeys.LOGIN_ADMIN) != null) {
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
		User admin = User.builder()
			.account(request.getParameter("account"))
			.password(request.getParameter("password"))
			.build();

		if (userService.login(admin) == null || !userService.isAdmin(admin)) {
			return View.builder()
				.isRedirect(true)
				.isValidationError(true)
				.path("/login")
				.build();
		}

		session.setAttribute(SessionKeys.LOGIN_ADMIN, admin.getAccount());

		return
			View.builder()
				.isRedirect(true)
				.path("/")
				.build();
	}
}
