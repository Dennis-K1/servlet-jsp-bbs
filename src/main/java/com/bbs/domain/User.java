package com.bbs.domain;

import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 사용자 정보 객체
 * DB
 * 	  PK 		: 	Long 타입
 *    TINYINT	:	BYTE 타입
 *    DATE		:   java.util.Date 타입
 */
@Getter
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
	private Date dateRegistered;

	/**
	 * 삭제여부 0: 미삭제, 1: 삭제
	 */
	private Byte userDeleted;

	/**
	 * 회원삭제일
	 */
	private Date dateDeleted;


	@Builder
	public User(Long roleId, String account, String password) {
		this.roleId = roleId;
		this.account = account;
		this.password = password;
	}
}
