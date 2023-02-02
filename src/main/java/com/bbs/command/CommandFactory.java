package com.bbs.command;

import com.bbs.command.admin.AdminCommandMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpServletRequest;

/**
 * 모든 Controller 에서 사용하는 Command 팩토리
 */
public abstract class CommandFactory {

	/**
	 * 요청 경로와 요청 CommandKey 에 맞는 Command 객체 반환
	 * @param request 클라이언트 요청
	 */
	public static Command getCommand(HttpServletRequest request) {

		Map<String, Map<String, Command>> commandMap = loadCommandMap();

		String commandName = request.getServletPath().substring(1);
		String commandKey = request.getPathInfo().substring(1);

		Command command = commandMap.get(commandName).get(commandKey);

		return command;
	}

	/**
	 * Map 객체에 요청 경로와 해당하는 CommandMap 취합하여 반환
	 */
	private static Map<String, Map<String, Command>> loadCommandMap() {

		Map<String, Map<String, Command>> commandMap = new ConcurrentHashMap();

		commandMap.put("admin", new AdminCommandMap());

		return commandMap;
	}
}

