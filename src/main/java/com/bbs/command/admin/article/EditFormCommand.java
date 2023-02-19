package com.bbs.command.admin.article;

import com.bbs.command.AdminCommands;
import com.bbs.command.Command;
import com.bbs.domain.View;
import com.bbs.domain.Article;
import com.bbs.service.ArticleService;
import com.bbs.util.CommandUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 게시글 수정 관련 커맨드
 */
public class EditFormCommand implements
	Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		ArticleService articleService = new ArticleService();
		Long articleId = Long.valueOf(request.getParameter("articleId"));
		Article article = articleService.getArticleById(articleId);
		request.setAttribute("article", article);

		return View.forwardTo(AdminCommands.ARTICLE_EDIT_FORM.getPath());
	}
}
