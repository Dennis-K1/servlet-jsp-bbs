package com.bbs.command.admin;

import com.bbs.command.Command;
import com.bbs.command.View;
import com.bbs.util.SessionKeys;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminIndexCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (session.getAttribute(SessionKeys.LOGIN_ADMIN) != null) {
			request.setAttribute("account", session.getAttribute(SessionKeys.LOGIN_ADMIN));
		}

		return View.builder()
			.path("/index")
			.build();
	}
}
