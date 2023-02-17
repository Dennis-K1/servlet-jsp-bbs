package com.bbs.command.admin.article;

import com.bbs.domain.Errors;
import com.bbs.properties.AdminCommands;
import com.bbs.command.Command;
import com.bbs.domain.View;
import com.bbs.domain.Article;
import com.bbs.domain.File;
import com.bbs.service.ArticleService;
import com.bbs.service.FileService;
import com.bbs.util.CommandUtil;
import com.bbs.properties.SessionKeys;
import com.oreilly.servlet.MultipartRequest;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 게시글 등록 관련 커맨드
 */
public class InputCommand implements
	Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		Long boardId = CommandUtil.getBoardIdByRequest(request);

		if (CommandUtil.isGETMethod(request)) {
			request.setAttribute("boardId", boardId);
			return View.forwardTo(AdminCommands.ARTICLE_INPUT.getPath());
		}

		FileService fileService = new FileService();
		ArticleService articleService = new ArticleService();


		MultipartRequest multipartRequest = fileService.getMultipartRequest(request);

		String title = multipartRequest.getParameter("title");
		String content = multipartRequest.getParameter("content");

		if (!isArticleInputValid(title,content)){
			return View.redirectTo(AdminCommands.NOTICE_INPUT.getPath(),
				Errors.VALIDATION_ERROR.getMessage());
		}

		Article article = Article.builder()
			.boardId(boardId)
			.account(CommandUtil.getUserAccountFromSession(request, SessionKeys.LOGIN_ADMIN))
			.title(multipartRequest.getParameter("title"))
			.content(multipartRequest.getParameter("content"))
			.build();


		int insertResult = (articleService.inputArticle(article));

		if (insertResult != 1) {
			throw new RuntimeException("게시글 등록 실패");
		}

		if (isFileUploaded(multipartRequest)) {
			File file = fileService.createFileVO(multipartRequest, article.getId());

			fileService.relocateFileFromTempDirectory(file);

			insertResult = fileService.inputFile(file);

			if (insertResult != 1) {
				throw new RuntimeException("파일 등록 실패");
			}
		}

		return View.redirectTo(AdminCommands.NOTICE_MANAGEMENT.getPath());
	}

	/**
	 * 입력값 서버 유효성 검증
	 *
	 * @param title 제목
	 * @param content 내용
	 * @return
	 */
	private Boolean isArticleInputValid(String title, String content) {
		if (Objects.equals("", title) || Objects.equals(null,title)) {
			return false;
		}
		if ("".equals(content) || Objects.equals(null,content)) {
			return false;
		}
		return true;
	}

	/**
	 * 파일 첨부 여부 확인
	 *
	 * @param multipartRequest
	 * @return
	 */
	private Boolean isFileUploaded(MultipartRequest multipartRequest) {
		if (Objects.equals(null, multipartRequest.getFilesystemName("file"))) {
			return false;
		}
		return true;
	}
}
