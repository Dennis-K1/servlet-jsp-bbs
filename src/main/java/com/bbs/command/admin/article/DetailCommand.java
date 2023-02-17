package com.bbs.command.admin.article;

import com.bbs.properties.AdminCommands;
import com.bbs.command.Command;
import com.bbs.domain.View;
import com.bbs.domain.Article;
import com.bbs.domain.File;
import com.bbs.service.ArticleService;
import com.bbs.service.FileService;
import com.bbs.util.CommandUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 게시글 상세 보기 관련 커맨드
 */
public class DetailCommand implements
	Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		ArticleService articleService = new ArticleService();

		if (CommandUtil.isGETMethod(request)) {
			Long articleId = Long.valueOf(request.getParameter("articleId"));
			Article article = articleService.getArticleById(articleId);
			articleService.increaseArticleViewsById(articleId);

			FileService fileService = new FileService();
			File file = fileService.getFileByArticleId(articleId);
			article.setImage(fileService.getEncodedImageFromFile(file));

			request.setAttribute("article", article);
			return View.forwardTo(AdminCommands.ARTICLE_DETAIL.getPath());
		}
		return null;
	}
}
