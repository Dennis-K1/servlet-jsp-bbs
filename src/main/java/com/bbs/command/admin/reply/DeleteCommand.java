package com.bbs.command.admin.reply;

import com.bbs.command.Command;
import com.bbs.domain.View;
import com.bbs.service.ArticleService;
import com.bbs.util.CommandUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		ArticleService articleService = new ArticleService();

		Long articleId = Long.valueOf(request.getParameter("articleId"));
		Long replyId = Long.valueOf(request.getParameter("replyId"));

		articleService.deleteReplyById(replyId);

		Long boardId = articleService.getBoardIdById(articleId);

		String path = CommandUtil.getPathByBoardId(boardId);

		return View.redirectTo(path + "/detail?articleId=" + articleId);
	}
}