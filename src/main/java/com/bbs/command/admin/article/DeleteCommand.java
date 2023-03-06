package com.bbs.command.admin.article;

import com.bbs.command.Command;
import com.bbs.domain.File;
import com.bbs.domain.View;
import com.bbs.service.ArticleService;
import com.bbs.service.FileService;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 게시글 삭제 관련 커맨드
 * 1. 게시글 정보 삭제 처리
 * 2. 게시글에 등록된 파일이 있다면 삭제
 * 3. 게시글에 해당하는 게시판으로 redirect
 */
public class DeleteCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		ArticleService articleService = new ArticleService();
		FileService fileService = new FileService();

		//TODO 유효성
		Long id = Long.valueOf(request.getParameter("articleId"));

		articleService.deleteArticleById(id);

		File file = fileService.getFileByArticleId(id);
		if (!Objects.equals(null, file)) {
			fileService.deleteDirectory(file);
		}

		String path = articleService.getBoardPathById(id);

		return View.redirectTo(path);
	}


}
