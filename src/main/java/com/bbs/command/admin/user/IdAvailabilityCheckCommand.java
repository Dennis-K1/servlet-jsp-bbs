package com.bbs.command.admin.user;

import com.bbs.command.AdminCommands;
import com.bbs.command.Command;
import com.bbs.domain.View;
import com.bbs.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 아이디 중복 여부 검사 커맨드
 * 1. 프론트에서 AJAX 형태로 중복 검사 요청
 * 2. 아이디 사용 가능 여부 부 확인 후 "idAvailability" 불린 설정하여 프론트 전달
 * 2. 프론트에서 "idAvailability" json 반환
 */
public class IdAvailabilityCheckCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		UserService userService = new UserService();

		String account = request.getParameter("account");

		request.setAttribute("idAvailability", true);

		if (!userService.isAccountAvailable(account)) {
			request.setAttribute("idAvailability", false);
		}

		return View.forwardTo(AdminCommands.USER_ID_AVAILABILITY_CHECK.getPath());
	}
}