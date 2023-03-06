package com.bbs.command.admin.article;

import com.bbs.command.AdminCommands;
import com.bbs.command.Command;
import com.bbs.domain.Errors;
import com.bbs.domain.View;
import com.bbs.service.ArticleService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 게시글 복구 커맨드
 */
public class RecoveryCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		ArticleService articleService = new ArticleService();

		String articleId = request.getParameter("articleId");
		if (!articleService.isValidArticleId(articleId)) {
			return View.forwardTo(AdminCommands.ERROR_HANDLER.getPath(),
				Errors.VALIDATION_ERROR.getMessage());
		}

		articleService.recoverArticleById(Long.valueOf(articleId));

		return View.redirectTo(AdminCommands.NOTICE_MANAGEMENT.getPath());
	}
}
