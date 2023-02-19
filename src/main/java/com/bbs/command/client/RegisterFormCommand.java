package com.bbs.command.client;

import com.bbs.command.ClientCommands;
import com.bbs.command.Command;
import com.bbs.domain.View;
import com.bbs.properties.SessionKeys;
import com.bbs.util.CommandUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 클라이언트 회원가입 화면 커맨드
 */
public class RegisterFormCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (CommandUtil.isUserLoggedIn(session, SessionKeys.LOGIN_CLIENT)) {
			return View.redirectTo(ClientCommands.INDEX.getPath());
		}
		return View.forwardTo(ClientCommands.REGISTER_FORM.getPath());
	}
}
