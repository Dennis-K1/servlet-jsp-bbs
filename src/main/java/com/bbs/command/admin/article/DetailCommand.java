package com.bbs.command.admin.article;

import com.bbs.command.AdminCommands;
import com.bbs.command.Command;
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

		//TODO 유효성 검사 (값 꺼내어 검사 후 Long 변환)
		Long articleId = Long.valueOf(request.getParameter("articleId"));

		Article article = articleService.getArticleById(articleId);
		articleService.increaseArticleViewsById(articleId);

		FileService fileService = new FileService();

		File file = fileService.getFileByArticleId(articleId);
		if (!Objects.equals(file,null)){
			article.setImage(fileService.getEncodedImageFromFile(file));
		}

		request.setAttribute("article", article);
		return View.forwardTo(AdminCommands.ARTICLE_DETAIL.getPath());
	}
}
