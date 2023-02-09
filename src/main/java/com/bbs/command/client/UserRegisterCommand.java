package com.bbs.command.client;

import com.bbs.command.ClientCommands;
import com.bbs.command.Command;
import com.bbs.command.View;
import com.bbs.domain.User;
import com.bbs.service.UserService;
import com.bbs.util.CommandUtil;
import com.bbs.util.Role;
import com.bbs.util.SessionKeys;
import java.io.IOException;
import java.net.http.HttpResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 클라이언트 유저 회원가입 관련 커맨드
 */
public class UserRegisterCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (CommandUtil.isGETMethod(request)) {
			if (CommandUtil.isUserLoggedIn(session, SessionKeys.LOGIN_CLIENT)) {
				return View.redirectTo(ClientCommands.INDEX.getPath());
			}
			return View.forwardTo(ClientCommands.REGISTER.getPath());
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

		return View.redirectTo(ClientCommands.LOGIN.getPath());
	}
}
