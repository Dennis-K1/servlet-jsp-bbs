package com.bbs.command.admin;

import com.bbs.command.Command;
import com.bbs.command.View;
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
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		UserService userService = new UserService();

		return View.builder()
			.path("/user")
			.build();
	}
}
