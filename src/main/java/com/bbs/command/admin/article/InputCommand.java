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
 */
public class InputCommand implements
	Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		Long boardId = CommandUtil.getBoardIdByRequest(request);

		FileService fileService = new FileService();
		ArticleService articleService = new ArticleService();

		MultipartRequest multipartRequest = fileService.getMultipartRequest(request);

		String title = multipartRequest.getParameter("title");
		String content = multipartRequest.getParameter("content");

		if (!isArticleInputValid(title,content)){
			return View.redirectTo(AdminCommands.NOTICE_INPUT.getPath(),
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


		int insertResult = (articleService.inputArticle(article));

		if (insertResult != 1) {
			FileUtils.cleanDirectory(new java.io.File(FileProperties.tempDirectory));
			throw new RuntimeException("게시글 등록 실패");
		}

		if (CommandUtil.isFileUploaded(multipartRequest)) {
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


}
