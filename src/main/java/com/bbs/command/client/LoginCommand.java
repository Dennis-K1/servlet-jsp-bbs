package com.bbs.command.client;

import com.bbs.command.AdminCommands;
import com.bbs.command.ClientCommands;
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
 * 클라이언트 로그인 실행 커맨드
 */
public class LoginCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();

		UserService userService = new UserService();
		User user = User.builder()
			.account(request.getParameter("account"))
			.password(request.getParameter("password"))
			.build();

		if (userService.login(user) == null) {
			return View.redirectTo(ClientCommands.LOGIN_FORM.getPath());
		}

		session.setAttribute(SessionKeys.LOGIN_CLIENT, user.getAccount());

		userService.increaseVisitCount(user);
		userService.updateLastLogin(user);


		String redirectURL = request.getParameter("redirectURL");

		if (CommandUtil.isStringEmpty(redirectURL)) {
			return View.redirectTo(ClientCommands.INDEX.getPath());
		}
		return View.redirectTo(redirectURL);
	}
}
