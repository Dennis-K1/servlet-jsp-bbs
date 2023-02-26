package com.bbs.command.admin.user;

import com.bbs.command.AdminCommands;
import com.bbs.command.Command;
import com.bbs.domain.Article;
import com.bbs.domain.File;
import com.bbs.domain.User;
import com.bbs.domain.View;
import com.bbs.service.ArticleService;
import com.bbs.service.FileService;
import com.bbs.service.UserService;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DetailCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		UserService userService = new UserService();
		ArticleService articleService = new ArticleService();

		String account = String.valueOf(request.getParameter("account"));

		//TODO 유저 없을 시 에러 처리
		if (account == null) {
			return null;
		}
		User user = userService.getUserByAccount(account);
		List<Article> articleList = articleService.getArticleListByUser(user);

		request.setAttribute("user", user);
		request.setAttribute("articleList", articleList);

		return View.forwardTo(AdminCommands.USER_DETAIL.getPath());
	}
}
