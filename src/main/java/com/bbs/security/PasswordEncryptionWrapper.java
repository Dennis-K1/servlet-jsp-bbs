package com.bbs.security;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 패스워드 인코딩을 위한 래퍼 객체
 */
public class PasswordEncryptionWrapper extends HttpServletRequestWrapper {

	/**
	 * Constructs a request object wrapping the given request.
	 *
	 * @param request the {@link HttpServletRequest} to be wrapped.
	 * @throws IllegalArgumentException if the request is null
	 */
	public PasswordEncryptionWrapper(HttpServletRequest request) {
		super(request);
	}

	/**
	 * 파라미터명이 Password 일 경우 패스워드 인코딩 후 반환
	 * @param name 파라미터명
	 *
	 * @return 파라미터 값
	 */
	@Override
	public String getParameter(String name) {
		if (name != null && name.equals("password")) {
			return encodePassword(super.getParameter("password"));
		}
		return super.getParameter(name);
	}

	/**
	 * 패스워드 인코딩 (SHA-512)
	 *
	 * @param password 패스워드 값
	 * @return 인코딩된 패스워드
	 */
	private String encodePassword(String password) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

		byte[] bytes = password.getBytes(Charset.forName("UTF-8"));
		messageDigest.update(bytes);

		return Base64
			.getEncoder()
			.encodeToString(messageDigest.digest());
	}
}
