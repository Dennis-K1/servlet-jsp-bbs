package com.bbs.util;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter({"/register", "/login"})
public class PasswordEncryptionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {

		PasswordEncryptionWrapper passwordEncryptionWrapper = new PasswordEncryptionWrapper((HttpServletRequest)request);
		chain.doFilter(passwordEncryptionWrapper, response);
	}

}
