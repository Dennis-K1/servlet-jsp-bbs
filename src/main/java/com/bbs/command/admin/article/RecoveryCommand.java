package com.bbs.command.admin.article;

import com.bbs.command.AdminCommands;
import com.bbs.command.Command;
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

		Long articleId = Long.valueOf(request.getParameter("articleId"));

		articleService.recoverArticleById(articleId);

		return View.redirectTo(AdminCommands.NOTICE_MANAGEMENT.getPath());
	}
}
