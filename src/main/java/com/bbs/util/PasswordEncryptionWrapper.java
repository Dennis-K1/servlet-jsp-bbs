package com.bbs.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

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

	@Override
	public String getParameter(String name) {
		if (name != null && name.equals("password")) {
			return encodePassword(super.getParameter("password"));
		}
		return super.getParameter(name);
	}

	private String encodePassword(String password) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		byte[] bytes = password.getBytes(Charset.forName("UTF-8"));
		messageDigest.update(bytes);

		return Base64
			.getEncoder()
			.encodeToString(messageDigest.digest());
	}
}
