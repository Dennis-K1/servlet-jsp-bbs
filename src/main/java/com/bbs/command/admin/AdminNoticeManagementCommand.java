package com.bbs.command.admin;

import com.bbs.command.AdminCommands;
import com.bbs.command.Command;
import com.bbs.command.View;
import com.bbs.domain.Article;
import com.bbs.domain.PageParameters;
import com.bbs.service.ArticleService;
import com.bbs.util.CommandUtil;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 공지사항 관리 목록 페이지 관련 커맨드
 */
public class AdminNoticeManagementCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		ArticleService articleService = new ArticleService();

		if (CommandUtil.isGETMethod(request)) {
			Long boardId = getBoardIdByRequest(request);
			String searchKeyword = request.getParameter("searchKeyword");
			String requestedPageNumber = request.getParameter("pageNumber");

			PageParameters pageParameters = PageParameters.builder()
				.searchKeyword(searchKeyword)
				.boardId(boardId)
				.build();

			int numberOfItems = articleService.getNumberOfArticlesBySearch(pageParameters);

			pageParameters.setPaginationElements(requestedPageNumber, numberOfItems);

			List<Article> noticeList = articleService.getArticleList(pageParameters);

			request.setAttribute("noticeList", noticeList);
			request.setAttribute("pageParameters", pageParameters);

			return View.forwardTo(AdminCommands.NOTICE_MANAGEMENT.getPath());
		}
		return null;
	}

	private Long getBoardIdByRequest(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		Map<String, Long> boardIdMap = new HashMap<>();
		boardIdMap.put(AdminCommands.NOTICE_MANAGEMENT.getPath(), 1L);
//		boardIdMap.put("community", 2L);
		return boardIdMap.get(requestURI);
	}
}
