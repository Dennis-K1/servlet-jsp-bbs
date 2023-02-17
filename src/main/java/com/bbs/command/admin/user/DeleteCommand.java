package com.bbs.command.admin.user;

import com.bbs.properties.AdminCommands;
import com.bbs.command.Command;
import com.bbs.domain.View;
import com.bbs.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 유저 삭제 관련 커맨드
 */
public class DeleteCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		UserService userService = new UserService();
		int id = Integer.parseInt(request.getParameter("userId"));
		userService.deleteUserById(id);
		return View.redirectTo(AdminCommands.USER_MANAGEMENT.getPath());
	}
}
