package com.bbs.command.admin;

import com.bbs.command.Command;
import java.util.concurrent.ConcurrentHashMap;

/**
 * admin 도메인의 Command 모음
 */
public class AdminCommandMap extends ConcurrentHashMap<String, Command> {
	public AdminCommandMap() {

		/**
		 * 테스트
		 */
		put("test", new TestCommand());
	}
}
