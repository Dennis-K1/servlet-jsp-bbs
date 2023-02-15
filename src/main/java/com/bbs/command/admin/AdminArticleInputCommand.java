package com.bbs.command.admin;

import com.bbs.command.AdminCommands;
import com.bbs.command.Command;
import com.bbs.command.View;
import com.bbs.util.CommandUtil;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminArticleInputCommand implements
	Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		if (CommandUtil.isGETMethod(request)) {
			Long boardId = getBoardIdByRequest(request);
			request.setAttribute("boardId", boardId);
			return View.forwardTo(AdminCommands.ARTICLE_INPUT.getPath());
		}
		return null;
	}

	private Long getBoardIdByRequest(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		Map<String, Long> boardIdMap = new HashMap<>();
		boardIdMap.put(AdminCommands.NOTICE_INPUT.getPath(), 1L);
//		boardIdMap.put("community", 2L);
		return boardIdMap.get(requestURI);
	}
}
