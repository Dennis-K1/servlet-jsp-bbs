package com.bbs.command.admin;

import com.bbs.command.AdminCommands;
import com.bbs.command.Command;
import com.bbs.domain.View;
import com.bbs.domain.User;
import com.bbs.domain.Errors;
import com.bbs.service.UserService;
import com.bbs.util.CommandUtil;
import com.bbs.properties.SessionKeys;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 어드민 로그인 관련 커맨드
 *
 * 1. 사용자 입력 정보 유효성 검증 후 틀리다면 로그인 화면으로 redirect
 * 2. 세션에 사용자 로그인 정보 추가후, 사용자 방문 횟수 및 마지막 접속일 업데이트
 * 3. 이전에 접속하려 했던 페이지 (redirectURL) 가 있는 경우 해당 페이지 반환
 */
public class LoginCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();

		UserService userService = new UserService();

		User admin = User.builder()
			.account(request.getParameter("account"))
			.password(request.getParameter("password"))
			.build();

		if (!userService.isUserValid(admin) || !userService.isAdmin(admin)) {
			return View.forwardTo(AdminCommands.ERROR_HANDLER.getPath(),
				Errors.LOGIN_FAILURE.getMessage());
		}

		session.setAttribute(SessionKeys.LOGIN_ADMIN, admin.getAccount());

		userService.increaseVisitCount(admin);

		userService.updateLastLogin(admin);

		String redirectURL = request.getParameter("redirectURL");

		if (CommandUtil.isStringEmpty(redirectURL)) {
			return View.redirectTo(AdminCommands.INDEX.getPath());
		}
		return View.redirectTo(redirectURL);
	}
}
