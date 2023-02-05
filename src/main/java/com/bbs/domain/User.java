package com.bbs.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 사용자 정보 객체
 */
@Getter
@Setter
@NoArgsConstructor
public class User {

	/**
	 * 테이블 PK
	 */
	private Long id;

	/**
	 * 권한 테이블 PK
	 */
	private Long roleId;

	/**
	 * 회원가입/로그인 ID
	 */
	private String account;

	/**
	 * 비밀번호
	 */
	private String password;

	/**
	 * 회원가입일
	 */
	private String dateRegistered;

	/**
	 * 삭제여부 0: 미삭제, 1: 삭제
	 */
	private Byte userDeleted;

	/**
	 * 회원삭제일
	 */
	private String dateDeleted;

}
