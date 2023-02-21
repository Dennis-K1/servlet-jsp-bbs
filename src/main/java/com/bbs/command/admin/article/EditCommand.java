package com.bbs.command.admin.article;

import com.bbs.command.AdminCommands;
import com.bbs.command.Command;
import com.bbs.domain.Article;
import com.bbs.domain.File;
import com.bbs.domain.View;
import com.bbs.service.ArticleService;
import com.bbs.service.FileService;
import com.bbs.util.CommandUtil;
import com.oreilly.servlet.MultipartRequest;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		FileService fileService = new FileService();
		ArticleService articleService = new ArticleService();

		MultipartRequest multipartRequest = fileService.getMultipartRequest(request);

		Long id = Long.valueOf(multipartRequest.getParameter("articleId"));
		String title = multipartRequest.getParameter("title");
		String content = multipartRequest.getParameter("content");

		Article article = Article.builder()
			.id(id)
			.title(title)
			.content(content)
			.build();

		int editResult = articleService.editArticle(article);
		if (editResult != 1) {
			throw new RuntimeException("수정 실패");
		}

		File previousFile = fileService.getFileByArticleId(id);

		String fileStatus = multipartRequest.getParameter("fileStatus");

		if (!CommandUtil.isFileUploaded(multipartRequest)) {
			if (fileStatus.equals("previous")) {
				return View.redirectTo(AdminCommands.NOTICE_DETAIL.getPath() + "?articleId=" + id);
			}
			if (previousFile != null) {
				fileService.deleteFile(previousFile);
			}
			return View.redirectTo(AdminCommands.NOTICE_DETAIL.getPath() + "?articleId=" + id);
		}

		if (previousFile != null) {
			fileService.deleteFile(previousFile);
		}

		File file = fileService.createFileVO(multipartRequest, article.getId());

		fileService.relocateFileFromTempDirectory(file);

		int inputResult = fileService.inputFile(file);
		if (inputResult != 1) {
			throw new RuntimeException("파일 등록 실패");
		}

		return View.redirectTo(AdminCommands.NOTICE_DETAIL.getPath() + "?articleId=" + id);
	}
}
