package com.bbs.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 프론트에 전송할 에러 메세지
 */
@Getter
@AllArgsConstructor
public enum Errors {

	/**
	 * 유효하지 않은 정보가 날라올 시
	 */
	VALIDATION_ERROR("입력하신 정보가 유효하지 않습니다."),

	/**
	 * 잘못된 비밀번호 입력 시
	 */
	INVALID_PASSWORD("입력하신 비밀번호가 맞지 않습니다."),

	/**
	 * 아이디 중복 검사, 이미 사용중인 아이디인 경우
	 */
	ACCOUNT_NOT_AVAILABLE("이미 사용중인 아이디입니다."),

	/**
	 * 존재하지 않는 사용자
	 */
	INVALID_USER("존재하지 않는 사용자입니다."),


	/**
	 * 게시글 등록 실패
	 */
	ARTICLE_INPUT_FAILURE("게시글 등록 실패"),

	/**
	 * 파일 등록 실패
	 */
	FILE_INPUT_FAILURE("파일 등록 실패"),
	/**
	 * 로그인 실패 시
	 */
	LOGIN_FAILURE("아이디나 비밀번호가 맞지 않습니다.");

	String message;
}
