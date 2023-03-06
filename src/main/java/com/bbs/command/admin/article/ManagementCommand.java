package com.bbs.command.admin.article;

import com.bbs.command.AdminCommands;
import com.bbs.command.Command;
import com.bbs.domain.File;
import com.bbs.domain.View;
import com.bbs.domain.Article;
import com.bbs.domain.PageParameters;
import com.bbs.service.ArticleService;
import com.bbs.service.FileService;
import com.bbs.util.CommandUtil;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * (공통) 게시글 관리 목록 페이지 관련 커맨드
 *
 * 1. 요청 받은 경로를 파악하여 해당 게시판 번호 조회
 * 2. 검색값 유효성 검증후 총 게시글 수 조회
 * 3. 총 게시글 수를 이용하여 기타 페이지네이션 관련 변수 초기화 후 해당 게시글 목록 조회
 * 4. 게시글 목록 순회하며 파일 정보가 있는 경우 파일 정보 대입
 * 5. 공통 게시글 관리 프론트 반환 (프론트에서 게시판 번호에 맞추어 렌더링)
 */
public class ManagementCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		ArticleService articleService = new ArticleService();

		PageParameters pageParameters = getPageParameters(request);

		int numberOfItems = articleService.getNumberOfArticlesBySearch(pageParameters);

		pageParameters.setPaginationElements(numberOfItems);

		List<Article> articleList = articleService.getArticleList(pageParameters);

		setImageIfExists(articleList);

		request.setAttribute("articleList", articleList);
		request.setAttribute("pageParameters", pageParameters);

		return View.forwardTo(AdminCommands.ARTICLE_MANAGEMENT.getPath());
	}

	/**
	 * 게시글 목록 순회하며, 대상 게시글에 등록된 파일(이미지) 이 있을 경우 정보 (Base64 인코딩 이미지) 입력
	 *
	 * @param articleList
	 */
	private void setImageIfExists(List<Article> articleList) throws IOException {
		for (Article article: articleList) {
			FileService fileService = new FileService();
			File file = fileService.getFileByArticleId(article.getId());
			if (!Objects.equals(file,null)){
				article.setImage(fileService.getEncodedImageFromFile(file));
			}
		}
	}

	/**
	 * 페이지네이션 관련 정보 객체 생성 (검색값, 게시판 번호 등)
	 *
	 * @param request
	 */
	private PageParameters getPageParameters(HttpServletRequest request) {

		Long boardId = CommandUtil.getBoardIdByRequest(request);

		//TODO 유효성 검사 (형태, 자료값 등)
		String searchKeyword = request.getParameter("searchKeyword");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String searchCategory = request.getParameter("searchCategory");
		String requestedPageNumber = request.getParameter("pageNumber");

		return PageParameters.builder()
			.searchKeyword(searchKeyword)
			.startDate(startDate)
			.endDate(endDate)
			.searchCategory(searchCategory)
			.pageNumber(CommandUtil.validatePageNumber(requestedPageNumber))
			.boardId(boardId)
			.build();
	}
}
