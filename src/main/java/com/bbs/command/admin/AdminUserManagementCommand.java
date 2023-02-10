package com.bbs.command.admin;

import com.bbs.command.AdminCommands;
import com.bbs.command.Command;
import com.bbs.command.View;
import com.bbs.dao.UserDAO;
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
			int pageNumberOFFSET = getPageNumberOFFSET(request.getParameter("pageNumber"));
			List<User> userList = userService.getUserList(pageNumberOFFSET);
			int pageNumber = pageNumberOFFSET / 10 + 1;
			int numberOfUsers = userService.getNumberOfUsers();
			request.setAttribute("userList", userList);
			request.setAttribute("numberOfUsers", numberOfUsers);
			request.setAttribute("pageNumber", pageNumber);
			request.setAttribute("pageSize", PAGE_SIZE);
			int pageCount = Math.round(numberOfUsers / PAGE_SIZE) + 1;
			request.setAttribute("pageCount", pageCount);
			int startPage = (pageNumber / 10) * 10 + 1;
			request.setAttribute("startPage", startPage);
			int endPage = (pageNumber / 10) * 10 + 10;
			if (endPage > pageCount) {
				endPage = pageCount;
			}
			request.setAttribute("endPage", endPage);
			List<Integer> displayedPageNumbers = new ArrayList<>();
			for (int page = startPage; page <= endPage; page++) {
				displayedPageNumbers.add(page);
			}
			request.setAttribute("displayedPageNumbers", displayedPageNumbers);
			return View.forwardTo(AdminCommands.USER_MANAGEMENT.getPath());
		}
		return null;
	}

	private int getPageNumberOFFSET(String pageNumber) {
		if (CommandUtil.isPositiveInteger(pageNumber)) {
			return (Integer.parseInt(pageNumber) - 1) * 10;
		}
		return 0;
	}
}
