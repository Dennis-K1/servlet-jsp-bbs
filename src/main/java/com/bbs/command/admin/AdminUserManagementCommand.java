package com.bbs.command.admin;

import com.bbs.command.AdminCommands;
import com.bbs.command.Command;
import com.bbs.command.View;
import com.bbs.dao.UserDAO;
import com.bbs.domain.User;
import com.bbs.service.UserService;
import com.bbs.util.CommandUtil;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminUserManagementCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		UserService userService = new UserService();

		if (CommandUtil.isGETMethod(request)){
			int pageNumber = validatePageNumber(request.getParameter("pageNumber"));
			List<User> userList = userService.getUserList(pageNumber);
			request.setAttribute("userList", userList);
			return View.forwardTo(AdminCommands.USER_MANAGEMENT.getPath());
		}
		return null;
	}

	private int validatePageNumber(String pageNumber) {
		if (CommandUtil.isPositiveInteger(pageNumber)) {
			return (Integer.parseInt(pageNumber) - 1) * 10;
		}
		return 0;
	}
}
