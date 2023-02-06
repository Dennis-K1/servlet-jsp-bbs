package com.bbs.command.admin;

import com.bbs.command.Command;
import com.bbs.command.CommandInformation;
import com.bbs.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 테스트 유저 커맨드
 */
public class UserCommand implements Command {

	@Override
	public CommandInformation execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		UserService userService = new UserService();
		userService.getUser("user1");

		return CommandInformation.builder()
			.path(ADMIN_VIEW_PATH + "user.jsp")
			.build();
	}
}
