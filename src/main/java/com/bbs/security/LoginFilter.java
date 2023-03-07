package com.bbs.security;

import com.bbs.command.AdminCommands;
import com.bbs.command.ClientCommands;
import com.bbs.properties.SessionKeys;
import com.bbs.util.CommandUtil;
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

	/**
	 * 로그인 필터가 적용되지 않는 요청 경로 모음
	 */
	private static final String[] whiteList = {"/", "/login", "/login/", "/loginForm",
		"/loginForm/",
		"/register", "/register/", "/registerForm", "/registerForm/",
		"/admin/login/", "/admin/login", "/admin/loginForm", "/admin/loginForm/",
		".css", ".js", ".png", ".jpg", "/admin/users/idAvailabilityCheck"};

	/**
	 * whiteList 에 해당하는 요청 경로일 경우 pass 아닌 경우 로그인 여부 확인 후 로그인 되어있을 경우 진행, 아닐 경우 로그인 페이지 이동
	 *
	 * @param request  the <code>ServletRequest</code> object contains the client's request
	 * @param response the <code>ServletResponse</code> object contains the filter's response
	 * @param chain    the <code>FilterChain</code> for invoking the next filter or the resource
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {

		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String requestURI = httpServletRequest.getRequestURI();
		String queryStrings = httpServletRequest.getQueryString();
		HttpSession session = httpServletRequest.getSession();

		if (isWhiteListPath(requestURI, whiteList)) {
			chain.doFilter(request, response);
			return;
		}

		//어드민 요청일 경우
		if (CommandUtil.isAdminRequest(requestURI)) {
			if (CommandUtil.isUserLoggedIn(session, SessionKeys.LOGIN_ADMIN)) {
				chain.doFilter(request, response);
				return;
			}
			if (requestURI.equals(AdminCommands.INDEX.getPath())) {
				httpServletResponse.sendRedirect(AdminCommands.LOGIN_FORM.getPath());
				return;
			}
			if (queryStrings != null) {
				httpServletResponse.sendRedirect(
					AdminCommands.LOGIN_FORM.getPath() + "?redirectURL=" + requestURI + "?" + queryStrings);
				return;
			}
			httpServletResponse.sendRedirect(
				AdminCommands.LOGIN_FORM.getPath() + "?redirectURL=" + requestURI);
			return;
		}

		//클라이언트 요청일 경우
		if (CommandUtil.isUserLoggedIn(session, SessionKeys.LOGIN_CLIENT)) {
			chain.doFilter(request, response);
			return;
		}
		httpServletResponse.sendRedirect("/login");
	}


	/**
	 * 화이트리스트 경로에 해당하는지 확인
	 *
	 * @param requestURI 요청 경로
	 * @param whiteList  화이트리스트
	 * @return boolean
	 */
	private boolean isWhiteListPath(String requestURI, String[] whiteList) {
		for (String whiteListPath : whiteList) {
			if (requestURI.equals(whiteListPath)) {
				return true;
			}
			if (requestURI.contains(".") && requestURI.substring(requestURI.lastIndexOf("."))
				.equals(whiteListPath)) {
				return true;
			}
		}
		return false;
	}
}
