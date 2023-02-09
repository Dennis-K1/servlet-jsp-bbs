package com.bbs.command;

import com.bbs.command.admin.AdminIndexCommand;
import com.bbs.command.admin.AdminLoginCommand;
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
	INDEX("/admin"),
	LOGIN("/admin/login"),


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
	}
}

