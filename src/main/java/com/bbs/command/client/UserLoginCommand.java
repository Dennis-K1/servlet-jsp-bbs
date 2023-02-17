package com.bbs.command.client;

import com.bbs.properties.ClientCommands;
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
 * 클라이언트 유저 로그인 관련 커맨드
 */
public class UserLoginCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (CommandUtil.isGETMethod(request)) {
			if (CommandUtil.isUserLoggedIn(session, SessionKeys.LOGIN_CLIENT)) {
				return View.redirectTo(ClientCommands.INDEX.getPath());
			}
			return View.forwardTo(ClientCommands.LOGIN.getPath());
		}

		UserService userService = new UserService();
		User user = User.builder()
			.account(request.getParameter("account"))
			.password(request.getParameter("password"))
			.build();

		if (userService.login(user) == null) {
			return View.redirectTo(ClientCommands.LOGIN.getPath(),
				Errors.LOGIN_FAILURE.getMessage());
		}

		session.setAttribute(SessionKeys.LOGIN_CLIENT, user.getAccount());

		return View.redirectTo(ClientCommands.INDEX.getPath());
	}
}
