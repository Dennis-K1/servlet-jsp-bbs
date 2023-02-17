package com.bbs.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Errors {

	VALIDATION_ERROR("입력하신 정보가 유효하지 않습니다."),
	INVALID_PASSWORD("입력하신 비밀번호가 맞지 않습니다."),
	LOGIN_FAILURE("아이디나 비밀번호가 맞지 않습니다.");

	String message;
}
