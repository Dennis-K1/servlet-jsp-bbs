package com.bbs.command.admin.reply;

import com.bbs.command.AdminCommands;
import com.bbs.command.Command;
import com.bbs.domain.Errors;
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
 *  답글(댓글) 대댓글 통합 등록 커맨드
 *
 *  1. 세션의 유저 정보를 이용하여 댓글 정보 객체 생성
 *  2. 해당 댓글이 대댓글임을 알리는 "replyId (원 댓글의 PK)"를 프론트에서 보내왔는지 확인 후 답글(댓글) 등록
 */
public class InputCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		ArticleService articleService = new ArticleService();

		String id = request.getParameter("articleId");
		if (!articleService.isValidArticleId(id)) {
			return View.forwardTo(AdminCommands.ERROR_HANDLER.getPath(),
				Errors.VALIDATION_ERROR.getMessage());
		}

		Long articleId = Long.valueOf(id);

		String replyContent = request.getParameter("content");
		if (!CommandUtil.isReplyValid(replyContent)) {
			return  View.forwardTo(AdminCommands.ERROR_HANDLER.getPath(),
				Errors.VALIDATION_ERROR.getMessage());
		}

		User userInfo = User.builder()
			.account(CommandUtil.getUserAccountFromSession(request, SessionKeys.LOGIN_ADMIN))
			.build();

		Reply reply = Reply.builder()
			.articleId(articleId)
			.user(userInfo)
			.content(replyContent)
			.build();

		if (isNestedReply(request)) {
			Long replyId = Long.valueOf(request.getParameter("replyId"));
			reply.setReplyId(replyId);
		}

		articleService.inputReply(reply);

		String path = articleService.getBoardPathById(articleId);

		return View.redirectTo(path + "/detail?articleId=" + articleId);
	}

	/**
	 * 프론트에서 대댓글임을 알리는 replyId 를 보내왔는지 확인
	 *
	 * @param request
	 */
	private boolean isNestedReply(HttpServletRequest request) {
		String replyId = request.getParameter("replyId");
		if (replyId != null) {
			return true;
		}
		return false;
	}
}
