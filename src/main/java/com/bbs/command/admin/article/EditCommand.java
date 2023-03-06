package com.bbs.command.admin.article;

import com.bbs.command.AdminCommands;
import com.bbs.command.Command;
import com.bbs.domain.Article;
import com.bbs.domain.Errors;
import com.bbs.domain.File;
import com.bbs.domain.View;
import com.bbs.service.ArticleService;
import com.bbs.service.FileService;
import com.bbs.util.CommandUtil;
import com.oreilly.servlet.MultipartRequest;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 게시글 수정 커맨드
 * <p>
 * 1. multipartRequest 로 입력값 확인 후 게시글 수정 2. 유효성 검사 실패 시 상세 페이지로 redirect 3. 해당 게시글의 기존 파일 상태 및 프론트
 * 파일 업로드 상태 확인 후 삭제,업로드 등 로직 실행
 */
public class EditCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		FileService fileService = new FileService();
		ArticleService articleService = new ArticleService();

		MultipartRequest multipartRequest = fileService.getMultipartRequest(request);

		//TODO 유효성 검사 필요 (검사 후 Long 변환)
		Long articleId = Long.valueOf(multipartRequest.getParameter("articleId"));

		String path = articleService.getBoardPathById(articleId);

		// 제목 유효성 검사
		String title = multipartRequest.getParameter("title");
		if (!CommandUtil.isTitleValid(title)) {
			return View.forwardTo(AdminCommands.ERROR_HANDLER.getPath(),
				Errors.VALIDATION_ERROR.getMessage());
		}

		// 내용 유효성 검사
		String content = multipartRequest.getParameter("content");
		if (!CommandUtil.isContentValid(content)) {
			return  View.forwardTo(AdminCommands.ERROR_HANDLER.getPath(),
				Errors.VALIDATION_ERROR.getMessage());
		}

		Article article = Article.builder()
			.id(articleId)
			.title(title)
			.content(content)
			.build();

		// 게시글 수정
		int editResult = articleService.editArticle(article);
		if (editResult != 1) {
			throw new RuntimeException(Errors.ARTICLE_INPUT_FAILURE.getMessage());
		}

		/*
		게시글 수정 화면에서 따로 파일이 업로드가 안 된 상태로 넘어왔을 경우 :
		- 프론트에서 수정 전 파일과 동일한 파일이다라는 'previous' 값을 넘겼을 경우,
		조작 없이 해당 게시글 상세페이지로 redirect
		-  수정화면에서 따로 파일을 업로드 안했지만 (기존 파일을 취소하여 업로드), 수정전 게시글에 속한
		파일이 있는 경우, 해당 파일 삭제
		 */
		File previousFile = fileService.getFileByArticleId(articleId);

		String fileStatus = multipartRequest.getParameter("fileStatus");

		if (!CommandUtil.isFileUploaded(multipartRequest)) {
			if (fileStatus.equals("previous")) {
				return View.redirectTo(path + "/detail?articleId=" + articleId);
			}
			if (previousFile != null) {
				fileService.deleteFile(previousFile);
			}
			return View.redirectTo(path + "/detail?articleId=" + articleId);
		}

		/*
		수정 화면에서 파일 업로드되어 넘어왔는데, 대상 게시글에 이미 속한 파일이 있는 경우 기존 파일 삭제
		 */
		if (previousFile != null) {
			fileService.deleteFile(previousFile);
		}

		File file = fileService.createFileVO(multipartRequest, articleId);

		int inputResult = fileService.inputFile(file);
		if (inputResult != 1) {
			throw new RuntimeException(Errors.FILE_INPUT_FAILURE.getMessage());
		}
		return View.redirectTo(path + "/detail?articleId=" + articleId);
	}
}
