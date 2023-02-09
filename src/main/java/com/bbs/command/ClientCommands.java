package com.bbs.command;

import com.bbs.command.client.IndexCommand;
import com.bbs.command.client.UserLoginCommand;
import com.bbs.command.client.UserRegisterCommand;
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
	INDEX("/"),
	LOGIN("/login"),
	REGISTER("/register"),


	THE_END_OF_ENUM(null);
	String path;

	/**
	 * 클라이언트 CommandMap
	 */
	@Getter
	private static final Map<String, Command> map = new HashMap<>();

	static {
		map.put(ClientCommands.INDEX.path, new IndexCommand());
		map.put(ClientCommands.LOGIN.path, new UserLoginCommand());
		map.put(ClientCommands.REGISTER.path, new UserRegisterCommand());
	}
}
