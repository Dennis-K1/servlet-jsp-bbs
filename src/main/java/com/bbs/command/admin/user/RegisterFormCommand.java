package com.bbs.command.admin.user;

import com.bbs.command.AdminCommands;
import com.bbs.command.Command;
import com.bbs.domain.View;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 회원 등록 폼 커맨드
 */
public class RegisterFormCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		return View.forwardTo(AdminCommands.USER_REGISTER_FORM.getPath());
	}
}
