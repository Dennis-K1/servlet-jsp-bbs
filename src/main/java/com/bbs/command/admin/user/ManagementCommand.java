package com.bbs.command.admin.user;

import com.bbs.command.AdminCommands;
import com.bbs.command.Command;
import com.bbs.domain.View;
import com.bbs.domain.PageParameters;
import com.bbs.domain.User;
import com.bbs.service.UserService;
import com.bbs.util.CommandUtil;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 유저 관리 목록 페이지 관련 커맨드
 * 1. 검색값 및 페이지 번호 이용하여 페이지네이션 객체 (PageParameters) 생성
 * 2. pageParameters 를 이용하여 검색값 기반 총 유저수 조회하여 반환
 * 3. 총 유저수를 이용하여 기타 페이지네이션 관련 변수를 초기화 후, 이를 통해 검색값 기반 유저 목록 조회
 */
public class ManagementCommand implements Command {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		UserService userService = new UserService();

		PageParameters pageParameters = getPageParameters(request);

		int numberOfItems = userService.getNumberOfUsersBySearch(pageParameters);

		pageParameters.setPaginationElements(numberOfItems);

		List<User> userList = userService.getUserList(pageParameters);

		request.setAttribute("userList", userList);
		request.setAttribute("pageParameters", pageParameters);

		return View.forwardTo(AdminCommands.USER_MANAGEMENT.getPath());
	}

	/**
	 * 페이지네이션 관련 정보 객체 생성 (검색값, 게시판 번호 등)
	 *
	 * @param request
	 */
	private PageParameters getPageParameters(HttpServletRequest request) {

		//TODO 유효성 검사 (형태, 자료값 등)
		String searchKeyword = request.getParameter("searchKeyword");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String requestedPageNumber = request.getParameter("pageNumber");

		return PageParameters.builder()
			.searchKeyword(searchKeyword)
			.startDate(startDate)
			.endDate(endDate)
			.pageNumber(CommandUtil.validatePageNumber(requestedPageNumber))
			.build();
	}
}
