package com.bbs.command.admin;

import com.bbs.command.Command;
import com.bbs.command.CommandInformation;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminIndexCommand implements Command {

	@Override
	public CommandInformation execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String method = request.getMethod();

		return CommandInformation.builder()
			.path(ADMIN_VIEW_PATH + "index.jsp")
			.build();
	}
}
