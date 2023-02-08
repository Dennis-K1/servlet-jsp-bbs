package com.bbs.util;

import com.bbs.domain.User;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class LoginFilter implements Filter {

	private static final String[] whiteList = {"/admin/login", "/admin/login/","/", "/login", "/login/", "/register",
		"/register/"};

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {

		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String requestURI = httpServletRequest.getRequestURI();
		HttpSession session = httpServletRequest.getSession();


		if (isWhiteListPath(requestURI, whiteList)) {
			chain.doFilter(request, response);
			return;
		}

		//어드민 요청일 경우
		if (CommandUtil.isAdminRequest(requestURI)) {
			if (isUserLoggedIn(session, SessionKeys.LOGIN_ADMIN)) {
				chain.doFilter(request, response);
				return;
			}
			httpServletResponse.sendRedirect("/admin/login");
			return;
		}

		//클라이언트 요청일 경우
		if (isUserLoggedIn(session, SessionKeys.LOGIN_CLIENT)) {
			chain.doFilter(request, response);
			return;
		}
		httpServletResponse.sendRedirect("/login");
	}

	/**
	 * 사용자가 로그인된 상태인지 확인
	 * @param session 세션
	 * @param sessionKey 로그인 시 세션 저장 키
	 * @return boolean
	 */
	private boolean isUserLoggedIn(HttpSession session, String sessionKey){
		String loginSessionValue = (String) session.getAttribute(sessionKey);
		if (loginSessionValue != null) {
			return true;
		}
		return false;
	}

	/**
	 * 화이트리스트 경로에 해당하는지 확인
	 * @param requestURI 요청 경로
	 * @param whiteList 화이트리스트
	 * @return boolean
	 */
	private boolean isWhiteListPath(String requestURI, String[] whiteList) {
		for (String whiteListPath : whiteList) {
			if (requestURI.equals(whiteListPath)) {
				return true;
			}
		}
		return false;
	}
}
