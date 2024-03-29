package com.bbs.properties;

/**
 * 세션에 저장될 키값 모음
 */
public interface SessionKeys {

	/**
	 * 어드민 로그인 시 세션 키
	 */
	String LOGIN_ADMIN = "LOGIN_ADMIN";

	/**
	 * 유저 로그인 시 세션 키
	 */
	String LOGIN_CLIENT = "LOGIN_CLIENT";
}
