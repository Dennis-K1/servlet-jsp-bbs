package com.bbs.command.admin.article;

import com.bbs.command.AdminCommands;
import com.bbs.command.Command;
import com.bbs.command.CommandFactory;
import com.bbs.domain.Errors;
import com.bbs.domain.View;
import com.bbs.domain.Article;
import com.bbs.domain.File;
import com.bbs.service.ArticleService;
import com.bbs.service.FileService;
import com.bbs.util.CommandUtil;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 게시글 상세 보기 관련 커맨드
 *
 * 1. 게시글 정보 가져오고 조회수 증가
 * 2. 해당 게시글 등록된 파일(이미지)이 있을 경우 파일 정보 (Base64 인코딩 이미지) 등록 후 반환
 */
public class DetailCommand implements
	Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		ArticleService articleService = new ArticleService();

		String id = request.getParameter("articleId");
		Long boardId = CommandUtil.getBoardIdByRequest(request);

		if (!articleService.isValidArticleId(id, boardId)) {
			return View.forwardTo(AdminCommands.ERROR_HANDLER.getPath(),
				Errors.VALIDATION_ERROR.getMessage());
		}

		Long articleId = Long.valueOf(id);

		articleService.increaseArticleViewsById(articleId);
		Article article = articleService.getArticleById(articleId);

		FileService fileService = new FileService();

		File file = fileService.getFileByArticleId(articleId);
		if (!Objects.equals(file,null)){
			article.setImage(fileService.getEncodedImageFromFile(file));
		}

		request.setAttribute("article", article);
		return View.forwardTo(AdminCommands.ARTICLE_DETAIL.getPath());
	}
}
