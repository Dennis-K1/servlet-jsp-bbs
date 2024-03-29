package com.bbs.security;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * 해당 경로 접속 시 PasswordEncryptionWrapper 로 Request 를 감싸 비밀번호 hash 처리
 */
@WebFilter({"/register", "/login", "/admin/login", "/admin/register"})
public class PasswordEncryptionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {

		PasswordEncryptionWrapper passwordEncryptionWrapper = new PasswordEncryptionWrapper((HttpServletRequest)request);
		chain.doFilter(passwordEncryptionWrapper, response);
	}

}
