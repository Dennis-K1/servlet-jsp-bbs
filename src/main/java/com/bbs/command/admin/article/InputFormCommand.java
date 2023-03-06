package com.bbs.command.admin.article;

import com.bbs.command.AdminCommands;
import com.bbs.command.Command;
import com.bbs.domain.View;
import com.bbs.util.CommandUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 게시글 등록 화면 커맨드
 */
public class InputFormCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		Long boardId = CommandUtil.getBoardIdByRequest(request);

		request.setAttribute("boardId", boardId);

		return View.forwardTo(AdminCommands.ARTICLE_INPUT_FORM.getPath());
	}
}
