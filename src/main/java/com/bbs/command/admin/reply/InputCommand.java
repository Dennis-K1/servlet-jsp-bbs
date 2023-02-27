package com.bbs.command.admin.reply;

import com.bbs.command.AdminCommands;
import com.bbs.command.Command;
import com.bbs.domain.Reply;
import com.bbs.domain.User;
import com.bbs.domain.View;
import com.bbs.properties.SessionKeys;
import com.bbs.service.ArticleService;
import com.bbs.util.CommandUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  답글 등록 커맨드
 */
public class InputCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		ArticleService articleService = new ArticleService();

		Long articleId = Long.valueOf(request.getParameter("articleId"));
		String replyContent = request.getParameter("content");

		User userInfo = User.builder()
			.account(CommandUtil.getUserAccountFromSession(request, SessionKeys.LOGIN_ADMIN))
			.build();

		Reply reply = Reply.builder()
			.articleId(articleId)
			.user(userInfo)
			.content(replyContent)
			.build();

		articleService.inputReply(reply);

		return View.redirectTo(AdminCommands.INQUIRY_DETAIL.getPath() + "?articleId=" + articleId);
	}
}
