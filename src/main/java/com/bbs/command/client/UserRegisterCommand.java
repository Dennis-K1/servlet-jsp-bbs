package com.bbs.command.client;

import com.bbs.command.Command;
import com.bbs.command.CommandInformation;
import com.bbs.domain.User;
import com.bbs.service.UserService;
import com.bbs.util.Role;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserRegisterCommand implements Command {

	@Override
	public CommandInformation execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String account = request.getParameter("account");
		String password = request.getParameter("password");

		User user = User.builder()
			.roleId(Role.USER.getRoleId())
			.account(account)
			.password(password)
			.build();

		UserService userService = new UserService();
		userService.registerUser(user);

		return CommandInformation.builder()
			.isRedirect(true)
			.path("/login")
			.build();
	}
}
