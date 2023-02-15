package com.bbs.command;

import com.bbs.command.admin.AdminArticleRecoveryCommand;
import com.bbs.command.admin.AdminIndexCommand;
import com.bbs.command.admin.AdminLoginCommand;
import com.bbs.command.admin.AdminArticleDeleteCommand;
import com.bbs.command.admin.AdminArticleInputCommand;
import com.bbs.command.admin.AdminNoticeManagementCommand;
import com.bbs.command.admin.AdminUserDeleteCommand;
import com.bbs.command.admin.AdminUserManagementCommand;
import com.bbs.command.admin.AdminUserRecoveryCommand;
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

	/**
	 * 게시글 삭제 처리 (모든 게시판 게시글 공통 적용 커맨드)
	 */
	ARTICLE_DELETE("/admin/articles/delete"),

	/**
	 * 게시글 복구 처리 (모든 게시판 게시글 공통 적용 커맨드)
	 */
	ARTICLE_RECOVERY("/admin/articles/recovery"),

	/**
	 * 게시글 작성 (모든 게시판 게시글 공통 적용 커맨드)
	 * - GET : 작성 form 작성 form (각각의 게시판에서 forward 하여 articles/input 경로는 클라이언트에 노출 안 됨)
	 * - POST : 업로드
	 */
	ARTICLE_INPUT("/admin/articles/input"),

	/**
	 * 공지사항 작성
	 * - GET : AdminArticlesInputCommand 에서 articles/input 으로 forward
	 */
	NOTICE_INPUT("/admin/notices/input"),

	/**
	 * 공지사항 관리
	 * - GET : 공지사항 리스트
	 */
	NOTICE_MANAGEMENT("/admin/notices"),


	/**
	 * 공지사항 상세페이지
	 */
	NOTICE_DETAIL("/admin/notices/${id}"),

	/**
	 * 공지사항 수정
	 * - GET : 수정 form
	 * - POST : 수정 실행
	 */
	NOTICE_UPDATE("/admin/notices/update"),

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
		map.put(AdminCommands.USER_DELETE.path, new AdminUserDeleteCommand());
		map.put(AdminCommands.USER_RECOVERY.path, new AdminUserRecoveryCommand());
		map.put(AdminCommands.NOTICE_MANAGEMENT.path, new AdminNoticeManagementCommand());
		map.put(AdminCommands.ARTICLE_DELETE.path, new AdminArticleDeleteCommand());
		map.put(AdminCommands.ARTICLE_RECOVERY.path, new AdminArticleRecoveryCommand());
		map.put(AdminCommands.NOTICE_INPUT.path, new AdminArticleInputCommand());
	}
}

