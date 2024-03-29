package com.bbs.command.admin.article;

import com.bbs.command.AdminCommands;
import com.bbs.command.Command;
import com.bbs.domain.Errors;
import com.bbs.domain.File;
import com.bbs.domain.View;
import com.bbs.domain.Article;
import com.bbs.service.ArticleService;
import com.bbs.service.FileService;
import com.bbs.util.CommandUtil;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 게시글 수정 관련 커맨드
 * <p>
 * 1. 게시글 정보 조회후, 파일이 있을 경우 함께 반환
 */
public class EditFormCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		ArticleService articleService = new ArticleService();

		Long boardId = CommandUtil.getBoardIdByRequest(request);
		String articleId = request.getParameter("articleId");

		if (!articleService.isValidArticleId(articleId, boardId)) {
			return View.forwardTo(AdminCommands.ERROR_HANDLER.getPath(),
				Errors.VALIDATION_ERROR.getMessage());
		}
		Article article = articleService.getArticleById(Long.valueOf(articleId));

		FileService fileService = new FileService();
		File file = fileService.getFileByArticleId(Long.valueOf(articleId));
		if (!Objects.equals(file, null)) {
			article.setImage(fileService.getEncodedImageFromFile(file));
		}

		request.setAttribute("article", article);
		return View.forwardTo(AdminCommands.ARTICLE_EDIT_FORM.getPath());
	}
}
