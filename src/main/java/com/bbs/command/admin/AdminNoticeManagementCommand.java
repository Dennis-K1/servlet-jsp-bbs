package com.bbs.command.admin;

import com.bbs.command.AdminCommands;
import com.bbs.command.Command;
import com.bbs.command.View;
import com.bbs.domain.Pagination;
import com.bbs.service.ArticleService;
import com.bbs.util.CommandUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminNoticeManagementCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		ArticleService articleService = new ArticleService();

		if (CommandUtil.isGETMethod(request)){
			int numberOfItems = articleService.getNumberOfArticles();
			String requestedPageNumber = request.getParameter("pageNumber");
			Pagination pagination = new Pagination(requestedPageNumber, numberOfItems);
			List<Article> noticeList = articleService.getArticleList(
				pagination.getPageNumberOffset());
			request.setAttribute("noticeList", noticeList);
			request.setAttribute("pagination", pagination);


			return View.forwardTo(AdminCommands.NOTICE_MANAGEMENT.getPath());
		}
		return null;
	}
}
