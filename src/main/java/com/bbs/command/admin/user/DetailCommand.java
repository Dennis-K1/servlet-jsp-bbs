package com.bbs.command.admin.user;

import com.bbs.command.AdminCommands;
import com.bbs.command.Command;
import com.bbs.domain.Article;
import com.bbs.domain.Errors;
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

/**
 * 유저 상세 정보 페이지 커맨드
 *
 * 1. 사용자 아이디가 유효하지 않을 경우 유저 관리 목록페이지로 에러 메세지와 redirect
 * 2. 사용자 정보 및, 사용자가 등록한 게시글 목록 조회하여 반환
 */
public class DetailCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		UserService userService = new UserService();
		ArticleService articleService = new ArticleService();

		String account = String.valueOf(request.getParameter("account"));

		if (!userService.isAccountValid(account)) {
			return View.forwardTo(AdminCommands.ERROR_HANDLER.getPath(), Errors.INVALID_USER.getMessage());
		}

		User user = userService.getUserByAccount(account);
		List<Article> articleList = articleService.getArticleListByUser(user);

		request.setAttribute("user", user);
		request.setAttribute("articleList", articleList);

		return View.forwardTo(AdminCommands.USER_DETAIL.getPath());
	}
}
