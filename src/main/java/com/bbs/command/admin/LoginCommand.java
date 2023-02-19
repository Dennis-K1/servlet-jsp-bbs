package com.bbs.command.admin;

import com.bbs.command.AdminCommands;
import com.bbs.command.Command;
import com.bbs.domain.View;
import com.bbs.domain.User;
import com.bbs.domain.Errors;
import com.bbs.service.UserService;
import com.bbs.util.CommandUtil;
import com.bbs.properties.SessionKeys;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 어드민 로그인 관련 커맨드
 */
public class LoginCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();

		UserService userService = new UserService();
		User admin = User.builder()
			.account(request.getParameter("account"))
			.password(request.getParameter("password"))
			.build();

		if (userService.login(admin) == null || !userService.isAdmin(admin)) {
			return View.redirectTo(AdminCommands.LOGIN_FORM.getPath(),
				Errors.LOGIN_FAILURE.getMessage());
		}

		session.setAttribute(SessionKeys.LOGIN_ADMIN, admin.getAccount());

		return View.redirectTo(AdminCommands.INDEX.getPath());
	}
}
