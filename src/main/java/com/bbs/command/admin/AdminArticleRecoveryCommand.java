package com.bbs.command.admin;

import com.bbs.command.AdminCommands;
import com.bbs.command.Command;
import com.bbs.command.View;
import com.bbs.dao.ArticleDAO;
import com.bbs.service.ArticleService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminArticleRecoveryCommand implements
	Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		ArticleService articleService = new ArticleService();
		Long id = Long.valueOf(request.getParameter("articleId"));
		articleService.recoverArticleById(id);
		return View.redirectTo(AdminCommands.NOTICE_MANAGEMENT.getPath());
	}
}
