package com.bbs.command.admin;

import com.bbs.command.AdminCommands;
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
 * 로그인 화면 커맨드
 *
 * 이미 로그인 되어있는 사용자가 해당 경로 접속 시 홈페이지로 이동
 */
public class LoginFormCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (CommandUtil.isUserLoggedIn(session, SessionKeys.LOGIN_ADMIN)) {
				return View.redirectTo(AdminCommands.INDEX.getPath());
			}

		return View.forwardTo(AdminCommands.LOGIN_FORM.getPath());
	}
}
