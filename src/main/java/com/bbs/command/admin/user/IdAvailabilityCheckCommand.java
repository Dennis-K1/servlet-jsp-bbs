package com.bbs.command.admin.user;

import com.bbs.command.AdminCommands;
import com.bbs.command.Command;
import com.bbs.domain.View;
import com.bbs.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 아이디 중복 여부 검사 커맨드
 */
public class IdAvailabilityCheckCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		UserService userService = new UserService();

		String account = request.getParameter("account");

		request.setAttribute("idAvailability", true);

		if (userService.getUserByAccount(account) != null) {
			request.setAttribute("idAvailability", false);
		}

		return View.forwardTo(AdminCommands.USER_ID_AVAILABILITY_CHECK.getPath());
	}
}