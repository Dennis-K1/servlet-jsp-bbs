package com.bbs.command.admin.user;

import com.bbs.command.AdminCommands;
import com.bbs.command.ClientCommands;
import com.bbs.command.Command;
import com.bbs.domain.Role;
import com.bbs.domain.User;
import com.bbs.domain.View;
import com.bbs.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 회원 등록 커맨드
 */
public class RegisterCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		UserService userService = new UserService();

		String account = request.getParameter("account");

		if (userService.getUserByAccount(account) != null) {
			return View.forwardTo(AdminCommands.USER_REGISTER_FORM.getPath(), "이미 사용중인 아이디입니다.");
		}

		String password = request.getParameter("password");

		User user = User.builder()
			.roleId(Role.USER.getRoleId())
			.account(account)
			.password(password)
			.build();

		userService.registerUser(user);

		return View.redirectTo(AdminCommands.USER_MANAGEMENT.getPath());
	}
}
