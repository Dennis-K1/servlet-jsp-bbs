package com.bbs.command.client;

import com.bbs.command.ClientCommands;
import com.bbs.command.Command;
import com.bbs.domain.Errors;
import com.bbs.domain.View;
import com.bbs.domain.User;
import com.bbs.service.UserService;
import com.bbs.domain.Role;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 클라이언트 회원가입 실행 커맨드
 */
public class RegisterCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		UserService userService = new UserService();

		String account = request.getParameter("account");

		if (userService.getUserByAccount(account) != null) {
			return View.redirectTo(ClientCommands.REGISTER_FORM.getPath());
		}

		String password = request.getParameter("password");

		User user = User.builder()
			.roleId(Role.USER.getRoleId())
			.account(account)
			.password(password)
			.build();

		userService.registerUser(user);

		return View.redirectTo(ClientCommands.LOGIN_FORM.getPath());
	}
}
