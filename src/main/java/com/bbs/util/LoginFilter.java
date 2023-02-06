package com.bbs.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class LoginFilter implements Filter {

	private static final String[] whiteList = {"/", "/login", "/admin/login"};

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		String requestURI = httpServletRequest.getRequestURI();

		for (String whiteListPath : whiteList) {
			if (requestURI.equals(whiteListPath)) {
				chain.doFilter(request,response);
				return;
			}
		}

		String account = (String) session.getAttribute("account");

		if (account != null) {
			chain.doFilter(request, response);
		} else {
			throw new ServletException("로그인된 사용자 없음");
		}
	}
}
