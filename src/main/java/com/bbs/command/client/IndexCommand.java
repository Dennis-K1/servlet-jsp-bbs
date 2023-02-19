package com.bbs.command.client;

import com.bbs.command.ClientCommands;
import com.bbs.command.Command;
import com.bbs.domain.View;
import com.bbs.properties.SessionKeys;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 클라이언트 인덱스(메인)페이지 커맨드
 */
public class IndexCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (session.getAttribute(SessionKeys.LOGIN_CLIENT) != null) {
			request.setAttribute("account", session.getAttribute(SessionKeys.LOGIN_CLIENT));
		}

		return View.forwardTo(ClientCommands.INDEX.getPath());
	}
}
