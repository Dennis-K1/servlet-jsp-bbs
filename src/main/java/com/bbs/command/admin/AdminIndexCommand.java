package com.bbs.command.admin;

import com.bbs.command.Command;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminIndexCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		return ADMIN_VIEW_PATH + "index.jsp";
	}
}
