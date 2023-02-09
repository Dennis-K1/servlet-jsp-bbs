package com.bbs.command;

import com.bbs.util.CommandUtil;
import java.util.Map;

/**
 * 모든 Controller 에서 사용하는 Command 팩토리
 */
public abstract class CommandFactory {


	/**
	 * 어드민 페이지 커맨드 맵
	 */
	private static final Map<String, Command> adminCommandMap = AdminCommands.getMap();

	/**
	 * 클라이언트 페이지 커맨드 맵
	 */
	private static final Map<String, Command> clientCommandMap = ClientCommands.getMap();


	/**
	 * 요청 경로에 해당하는 Command 객체 반환
	 *
	 * @param requestURI 요청 경로
	 */
	public static Command getCommand(String requestURI) {

		if (CommandUtil.isAdminRequest(requestURI)) {
			return adminCommandMap.get(requestURI);
		}

		return clientCommandMap.get(requestURI);
	}
}

