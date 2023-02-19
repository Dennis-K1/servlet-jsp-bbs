package com.bbs.command.admin.user;

import com.bbs.command.AdminCommands;
import com.bbs.command.Command;
import com.bbs.domain.View;
import com.bbs.domain.PageParameters;
import com.bbs.domain.User;
import com.bbs.service.UserService;
import com.bbs.util.CommandUtil;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 유저 관리 목록 페이지 관련 커맨드
 */
public class ManagementCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		UserService userService = new UserService();

		if (CommandUtil.isGETMethod(request)) {

			PageParameters pageParameters = new PageParameters();

			int numberOfItems = userService.getNumberOfUsers();
			String requestedPageNumber = request.getParameter("pageNumber");

			 pageParameters.setPaginationElements(requestedPageNumber, numberOfItems);

			List<User> userList = userService.getUserList(pageParameters.getPageNumberOffset());
			request.setAttribute("userList", userList);
			request.setAttribute("PageParameters", pageParameters);
			return View.forwardTo(AdminCommands.USER_MANAGEMENT.getPath());
		}
		return null;
	}


}
