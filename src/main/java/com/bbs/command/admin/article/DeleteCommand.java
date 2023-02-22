package com.bbs.command.admin.article;

import com.bbs.command.AdminCommands;
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
 */
public class DeleteCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		ArticleService articleService = new ArticleService();
		FileService fileService = new FileService();

		Long id = Long.valueOf(request.getParameter("articleId"));

		articleService.deleteArticleById(id);

		File file = fileService.getFileByArticleId(id);
		if (!Objects.equals(null, file)) {
			fileService.deleteDirectory(file);
		}

		return View.redirectTo(AdminCommands.NOTICE_MANAGEMENT.getPath());
	}

}
