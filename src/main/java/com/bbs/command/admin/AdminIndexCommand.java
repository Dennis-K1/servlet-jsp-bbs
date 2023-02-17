package com.bbs.command.admin;

import com.bbs.properties.AdminCommands;
import com.bbs.command.Command;
import com.bbs.domain.View;
import com.bbs.util.CommandUtil;
import com.bbs.properties.SessionKeys;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 어드민 인덱스(메인)페이지 관련 커맨드
 */
public class AdminIndexCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (CommandUtil.isUserLoggedIn(session, SessionKeys.LOGIN_ADMIN)) {
			request.setAttribute("account", session.getAttribute(SessionKeys.LOGIN_ADMIN));
		}

		return View.forwardTo(AdminCommands.INDEX.getPath());
	}
}
