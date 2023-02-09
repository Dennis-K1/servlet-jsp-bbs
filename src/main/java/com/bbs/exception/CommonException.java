package com.bbs.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonException {

	VALIDATION_ERROR("입력하신 정보가 유효하지 않습니다."),
	LOGIN_FAILURE("아이디나 비밀번호가 맞지 않습니다.");

	String message;
}
