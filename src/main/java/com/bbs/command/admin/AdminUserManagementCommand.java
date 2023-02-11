package com.bbs.command.admin;

import com.bbs.command.AdminCommands;
import com.bbs.command.Command;
import com.bbs.command.View;
import com.bbs.dao.UserDAO;
import com.bbs.domain.Pagination;
import com.bbs.domain.User;
import com.bbs.service.UserService;
import com.bbs.util.CommandUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminUserManagementCommand implements Command {

	private final int PAGE_SIZE = 10;

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		UserService userService = new UserService();

		if (CommandUtil.isGETMethod(request)) {
			int numberOfItems = userService.getNumberOfUsers();
			String requestedPageNumber = request.getParameter("pageNumber");
			Pagination pagination = new Pagination(requestedPageNumber, numberOfItems);
			List<User> userList = userService.getUserList(pagination.getPageNumberOffset());
			request.setAttribute("userList", userList);
			request.setAttribute("pagination", pagination);
			return View.forwardTo(AdminCommands.USER_MANAGEMENT.getPath());
		}
		return null;
	}


}
