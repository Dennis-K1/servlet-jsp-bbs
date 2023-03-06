package com.bbs.command.admin.article;

import com.bbs.domain.Errors;
import com.bbs.command.AdminCommands;
import com.bbs.command.Command;
import com.bbs.domain.User;
import com.bbs.domain.View;
import com.bbs.domain.Article;
import com.bbs.domain.File;
import com.bbs.properties.FileProperties;
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
import org.apache.commons.io.FileUtils;

/**
 * 게시글 등록 커맨드
 *
 * 1. request 객체를 이용하여 게시판 번호 및 경로 조회
 * 2. multipartRequest 를 통해 입력된 게시글 정보 조회, 유효성 검사 실패시 등록 페이지로 redirect
 * 3. 게시글 db 등록
 * 4. 파일 저장 및 정보 db 등록
 * 5. 해당 게시판 목록으로 redirect
 */
public class InputCommand implements Command {

	private final Long GALLERY_BOARD_ID = 4L;

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		FileService fileService = new FileService();
		ArticleService articleService = new ArticleService();

		Long boardId = CommandUtil.getBoardIdByRequest(request);
		String path = CommandUtil.getPathByBoardId(boardId);

		MultipartRequest multipartRequest = fileService.getMultipartRequest(request);

		// 제목 유효성 검사
		String title = multipartRequest.getParameter("title");
		if (!CommandUtil.isTitleValid(title)) {
			return  View.forwardTo(AdminCommands.ERROR_HANDLER.getPath(),
				Errors.VALIDATION_ERROR.getMessage());
		}

		// 내용 유효성 검사
		String content = multipartRequest.getParameter("content");
		if (!CommandUtil.isContentValid(content)) {
			return  View.forwardTo(AdminCommands.ERROR_HANDLER.getPath(),
				Errors.VALIDATION_ERROR.getMessage());
		}

		// 갤러리 게시글이 파일 없이 등록되었을 경우 유효성 검증 실패
		if (isGalleryArticleWithoutFile(multipartRequest, boardId)) {
			return  View.forwardTo(AdminCommands.ERROR_HANDLER.getPath(),
				Errors.VALIDATION_ERROR.getMessage());
		}

		User userInfo = User.builder()
			.account(CommandUtil.getUserAccountFromSession(request, SessionKeys.LOGIN_ADMIN))
			.build();

		Article article = Article.builder()
			.boardId(boardId)
			.user(userInfo)
			.title(multipartRequest.getParameter("title"))
			.content(multipartRequest.getParameter("content"))
			.build();

		// 게시글 등록
		int insertResult = (articleService.inputArticle(article));
		if (insertResult != 1) {
			FileUtils.cleanDirectory(new java.io.File(FileProperties.tempDirectory));
			throw new RuntimeException(Errors.ARTICLE_INPUT_FAILURE.getMessage());
		}

		if (CommandUtil.isFileUploaded(multipartRequest)) {
			File file = fileService.createFileVO(multipartRequest, article.getId());

			// 이미지 확장자 유효성 검사
			if (!CommandUtil.isImageExtensionValid(file.getExtension())) {
				articleService.deleteArticleById(article.getId());
				return View.forwardTo(AdminCommands.ERROR_HANDLER.getPath(),
					Errors.VALIDATION_ERROR.getMessage());
			}

			// 파일 등록
			insertResult = fileService.inputFile(file);
			if (insertResult != 1) {
				throw new RuntimeException(Errors.FILE_INPUT_FAILURE.getMessage());
			}
		}
		return View.redirectTo(path);
	}

	/**
	 * 갤러리 게시글 파일 업로드 여부 확인
	 *
	 * @param multipartRequest 요청 객체
	 * @param boardId 게시판 번호
	 * @return
	 */
	private boolean isGalleryArticleWithoutFile(MultipartRequest multipartRequest, Long boardId) {
		if (!CommandUtil.isFileUploaded(multipartRequest) && boardId == GALLERY_BOARD_ID) {
			return true;
		}
		return false;
	}
}
