package com.bbs.command;

import com.bbs.command.admin.AdminIndexCommand;
import com.bbs.command.admin.AdminLoginCommand;
import com.bbs.command.admin.AdminUserManagementCommand;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 어드민 Command 경로 및 CommandMap 을 관리하는 객체
 */
@Getter
@AllArgsConstructor
public enum AdminCommands {

	/**
	 * 메인페이지 반환
	 */
	INDEX("/admin"),

	/**
	 * 로그인
	 * - GET : 로그인 form
	 * - POST : 로그인 실행
	 */
	LOGIN("/admin/login"),

	/**
	 * 유저 관리
	 * - GET : 유저 리스트
	 */
	USER_MANAGEMENT("/admin/users"),

	/**
	 * 유저 삭제
	 */
	USER_DELETE("/admin/users/delete"),

	/**
	 * 유저 복구
	 */
	USER_RECOVERY("/admin/users/recovery"),

	/*
	편의를 위한 enum 끝 표시 //TODO THE_END_OF_ENUM 삭제
	 */
	THE_END_OF_ENUM(null);
	String path;

	/**
	 * 어드민 CommandMap
	 */
	@Getter
	private static final Map<String, Command> map = new HashMap<>();

	static {
		map.put(AdminCommands.LOGIN.path, new AdminLoginCommand());
		map.put(AdminCommands.INDEX.path, new AdminIndexCommand());
		map.put(AdminCommands.USER_MANAGEMENT.path, new AdminUserManagementCommand());
	}
}

