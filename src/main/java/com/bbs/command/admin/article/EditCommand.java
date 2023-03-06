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

/**
 * 게시글 수정 커맨드
 *
 * 1. multipartRequest 로 입력값 확인 후 게시글 수정
 * 2. 해당 게시글의 기존 파일 상태 및 프론트 파일 업로드 상태 확인 후 삭제,업로드 등 로직 실행
 *
 */
public class EditCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		FileService fileService = new FileService();
		ArticleService articleService = new ArticleService();

		MultipartRequest multipartRequest = fileService.getMultipartRequest(request);

		//TODO 유효성 검사 필요 (검사 후 Long 변환)
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
			throw new RuntimeException("게시글 수정 실패");
		}

		File previousFile = fileService.getFileByArticleId(id);

		String fileStatus = multipartRequest.getParameter("fileStatus");

		String path = articleService.getBoardPathById(id);


		/*
		게시글 수정 화면에서 따로 파일이 업로드가 안 된 상태로 넘어왔을 경우 :
		- 프론트에서 수정 전 파일과 동일한 파일이다라는 'previous' 값을 넘겼을 경우,
		조작 없이 해당 게시글 상세페이지로 redirect
		-  수정화면에서 따로 파일을 업로드 안했지만 (기존 파일을 취소하여 업로드), 수정전 게시글에 속한
		파일이 있는 경우, 해당 파일 삭제
		 */
		if (!CommandUtil.isFileUploaded(multipartRequest)) {
			if (fileStatus.equals("previous")) {
				return View.redirectTo(path + "/detail?articleId=" + id);
			}
			if (previousFile != null) {
				fileService.deleteFile(previousFile);
			}
			return View.redirectTo(path + "/detail?articleId=" + id);
		}

		/*
		수정 화면에서 파일 업로드되어 넘어왔는데, 대상 게시글에 이미 속한 파일이 있는 경우 기존 파일 삭제
		 */
		if (previousFile != null) {
			fileService.deleteFile(previousFile);
		}

		File file = fileService.createFileVO(multipartRequest, article.getId());

		int inputResult = fileService.inputFile(file);

		if (inputResult != 1) {
			throw new RuntimeException("파일 등록 실패");
		}
		return View.redirectTo(AdminCommands.NOTICE_DETAIL.getPath() + "?articleId=" + id);
	}
}
