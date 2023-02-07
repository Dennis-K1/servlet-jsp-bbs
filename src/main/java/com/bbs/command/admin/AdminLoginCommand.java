package com.bbs.command.admin;

import com.bbs.command.Command;
import com.bbs.command.CommandInformation;
import com.bbs.domain.User;
import com.bbs.service.UserService;
import com.bbs.util.CommandUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.javassist.NotFoundException;

public class AdminLoginCommand implements Command {

	@Override
	public CommandInformation execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String method = request.getMethod();
		if (CommandUtil.isGETMethod(request)) {
			return CommandInformation.builder()
				.path(ADMIN_VIEW_PATH + "login.jsp")
				.build();
		}

		User admin = User.builder()
			.account(request.getParameter("account"))
			.password(request.getParameter("password"))
			.build();

		UserService userService = new UserService();

		if (!userService.isExistingUser(admin) || !userService.isAdmin(admin)) {
			throw new ServletException("존재하지 않는 어드민");
		}

		HttpSession session = request.getSession();
		session.setAttribute("account", admin.getAccount());

		return
			CommandInformation.builder()
				.isRedirect(true)
				.path("/admin")
				.build();
	}
}
