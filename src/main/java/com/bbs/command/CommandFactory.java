package com.bbs.command;

import com.bbs.command.admin.AdminIndexCommand;
import com.bbs.command.admin.AdminLoginCommand;
import com.bbs.command.client.IndexCommand;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 모든 Controller 에서 사용하는 Command 팩토리
 */
public abstract class CommandFactory {


	/**
	 * 어드민 페이지 커맨드 맵
	 */
	private static final Map<String, Command> adminCommandMap = loadAdminCommandMap();

	/**
	 * 클라이언트 페이지 커맨드 맵
	 */
	private static final Map<String, Command> clientCommandMap = loadClientCommandMap();


	/**
	 * 요청 경로에 해당하는 Command 객체 반환
	 *
	 * @param requestURI 요청 경로
	 */
	public static Command getCommand(String requestURI) {

		if (requestURI.length() > 1 && requestURI.endsWith("/")) {
			requestURI = requestURI.substring(0, requestURI.length() - 1);
		}

		if (isAdminRequest(requestURI)) {
			return adminCommandMap.get(requestURI);
		}

		return clientCommandMap.get(requestURI);
	}


	/**
	 * 어드민 페이지 커맨드 맵
	 */
	private static Map<String, Command> loadAdminCommandMap() {

		Map<String, Command> commandMap = new ConcurrentHashMap();

		commandMap.put("/admin", new AdminIndexCommand());
		commandMap.put("/admin/login", new AdminLoginCommand());

		return commandMap;
	}

	/**
	 * 클라이언트 페이지 커맨드 맵
	 */
	private static Map<String, Command> loadClientCommandMap() {

		Map<String, Command> commandMap = new ConcurrentHashMap();

		commandMap.put("/", new IndexCommand());

		return commandMap;
	}

	/**
	 * 경로명 시작이 어드민 페이지에 해당하는 "admin"인지 확인
	 * @param requestURI 요청 경로
	 * @return admin 페이지 요청일 시 true
	 */
	private static boolean isAdminRequest(String requestURI) {
		String[] dividedPaths = requestURI.split("/");
		if (dividedPaths.length > 1 && dividedPaths[1].equals("admin")) {
			return true;
		}
		return false;
	}
}

