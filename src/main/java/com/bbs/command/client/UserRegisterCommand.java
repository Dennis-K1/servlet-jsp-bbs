package com.bbs.command.client;

import com.bbs.command.Command;
import com.bbs.command.View;
import com.bbs.domain.User;
import com.bbs.service.UserService;
import com.bbs.util.CommandUtil;
import com.bbs.util.Role;
import com.bbs.util.SessionKeys;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserRegisterCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (CommandUtil.isGETMethod(request)) {

			if (session.getAttribute(SessionKeys.LOGIN_CLIENT)!= null) {
				return View.builder()
					.isRedirect(true)
					.path("/")
					.build();
			}

			return View.builder()
				.path("/registerForm")
				.build();
		}

		String account = request.getParameter("account");
		String password = request.getParameter("password");

		User user = User.builder()
			.roleId(Role.USER.getRoleId())
			.account(account)
			.password(password)
			.build();

		UserService userService = new UserService();
		userService.registerUser(user);


		return View.builder()
			.isRedirect(true)
			.path("login")
			.build();
	}
}
