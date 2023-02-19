package com.bbs.command;

import com.bbs.command.client.IndexCommand;
import com.bbs.command.client.LoginCommand;
import com.bbs.command.client.LoginFormCommand;
import com.bbs.command.client.RegisterCommand;
import com.bbs.command.client.RegisterFormCommand;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 클라이언트 Command 경로 및 CommandMap 을 관리하는 객체
 */
@Getter
@AllArgsConstructor
public enum ClientCommands {

	/**
	 * 메인 페이지
	 */
	INDEX("/"),

	/**
	 * 로그인
	 */
	LOGIN("/login"),

	/**
	 * 로그인 화면
	 */
	LOGIN_FORM("/loginForm"),

	/**
	 * 회원가입
	 */
	REGISTER("/register"),

	/**
	 * 회원가입 화면
	 */
	REGISTER_FORM("/registerForm"),

	/*
	편의를 위한 enum 끝 표시 //TODO THE_END_OF_ENUM 삭제
	 */
	THE_END_OF_ENUM(null);
	String path;

	/**
	 * 클라이언트 CommandMap
	 */
	@Getter
	private static final Map<String, Command> map = new HashMap<>();

	static {
		map.put(ClientCommands.INDEX.path, new IndexCommand());
		map.put(ClientCommands.LOGIN_FORM.path, new LoginFormCommand());
		map.put(ClientCommands.LOGIN.path, new LoginCommand());
		map.put(ClientCommands.REGISTER_FORM.path, new RegisterFormCommand());
		map.put(ClientCommands.REGISTER.path, new RegisterCommand());
	}
}
