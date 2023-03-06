package com.bbs.command.admin.reply;

import com.bbs.command.AdminCommands;
import com.bbs.command.Command;
import com.bbs.domain.Errors;
import com.bbs.domain.View;
import com.bbs.service.ArticleService;
import com.bbs.util.CommandUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 답글(댓글) 삭제 커맨드
 */
public class DeleteCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		ArticleService articleService = new ArticleService();

		String articleId = request.getParameter("articleId");
		if (!articleService.isValidArticleId(articleId)) {
			return View.forwardTo(AdminCommands.ERROR_HANDLER.getPath(),
				Errors.VALIDATION_ERROR.getMessage());
		}

		String replyId = request.getParameter("replyId");
		if (!articleService.isValidReplyId(replyId)) {
			return View.forwardTo(AdminCommands.ERROR_HANDLER.getPath(),
				Errors.VALIDATION_ERROR.getMessage());
		}

		articleService.deleteReplyById(Long.valueOf(replyId));

		String path = articleService.getBoardPathById(Long.valueOf(articleId));

		return View.redirectTo(path + "/detail?articleId=" + articleId);
	}
}
