package com.bbs.command.admin.user;

import com.bbs.command.AdminCommands;
import com.bbs.command.ClientCommands;
import com.bbs.command.Command;
import com.bbs.domain.Errors;
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
 *
 * 1. 아이디 중복 확인, 중복이라면 에러메시지와 함께 등록화면 redirect
 * 2. 사용자 등록 후 유저 관리 목록 페이지 redirect
 */
public class RegisterCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		UserService userService = new UserService();

		String account = request.getParameter("account");

		if (!userService.isAccountAvailable(account)) {
			return View.redirectTo(AdminCommands.USER_REGISTER_FORM.getPath(), Errors.ACCOUNT_NOT_AVAILABLE.getMessage());
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
