package com.bbs.command.admin.article;

import com.bbs.command.AdminCommands;
import com.bbs.command.Command;
import com.bbs.domain.View;
import com.bbs.domain.Article;
import com.bbs.domain.PageParameters;
import com.bbs.service.ArticleService;
import com.bbs.util.CommandUtil;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 공지사항 관리 목록 페이지 관련 커맨드
 */
public class ManagementCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		ArticleService articleService = new ArticleService();

		Long boardId = CommandUtil.getBoardIdByRequest(request);

		String searchKeyword = request.getParameter("searchKeyword");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String requestedPageNumber = request.getParameter("pageNumber");

		PageParameters pageParameters = PageParameters.builder()
			.searchKeyword(searchKeyword)
			.startDate(startDate)
			.endDate(endDate)
			.boardId(boardId)
			.build();

		int numberOfItems = articleService.getNumberOfArticlesBySearch(pageParameters);

		pageParameters.setPaginationElements(requestedPageNumber, numberOfItems);

		List<Article> articleList = articleService.getArticleList(pageParameters);

		request.setAttribute("articleList", articleList);
		request.setAttribute("pageParameters", pageParameters);

		return View.forwardTo(AdminCommands.ARTICLE_MANAGEMENT.getPath());
	}
}
