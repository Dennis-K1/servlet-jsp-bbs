package com.bbs.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * request 처리를 위한 Command 인터페이스
 */
public interface Command {
	String execute(
		HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException;
}
