package com.bbs.properties;

import com.bbs.command.Command;
import com.bbs.command.admin.article.EditCommand;
import com.bbs.command.admin.article.DetailCommand;
import com.bbs.command.admin.AdminIndexCommand;
import com.bbs.command.admin.user.LoginCommand;
import com.bbs.command.admin.article.InputCommand;
import com.bbs.command.admin.article.AdminNoticeManagementCommand;
import com.bbs.command.admin.user.DeleteCommand;
import com.bbs.command.admin.user.ManagementCommand;
import com.bbs.command.admin.user.RecoveryCommand;
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
	 * - GET : 공통 작성 폼 경로 (게시판 (요청 경로) 에 맞추어 반환)
	 * - POST : 게시글 업로드 처리
	 */
	ARTICLE_INPUT("/admin/articles/input"),

	/**
	 * 게시글 상세 (모든 게시판 게시글 공통 적용 커맨드)
	 * - 공통 상세페이지 경로
	 */
	ARTICLE_DETAIL("/admin/articles/detail"),

	/**
	 * 게시글 수정
	 * - GET : 공통 수정 폼 경로 (게시판 (요청 경로) 에 맞추어 반환)
	 * - POST : 수정 실행
	 */
	ARTICLE_EDIT("/admin/articles/edit"),

	/**
	 * 공지사항 작성
	 * - GET : AdminArticlesInputCommand 에서 articles/input 로 forward
	 */
	NOTICE_INPUT("/admin/notices/input"),

	/**
	 * 공지사항 관리
	 * - GET : 공지사항 리스트
	 */
	NOTICE_MANAGEMENT("/admin/notices"),


	/**
	 * 공지사항 상세페이지 (parameter 로 articleId 전달)
	 * - GET : AdminArticlesDetailCommand 에서 articles/detail 로 forward
	 */
	NOTICE_DETAIL("/admin/notices/detail"),

	/**
	 * 공지사항 수정
	 * - GET : AdminArticlesDetailCommand 에서 articles/edit 로 forward
	 */
	NOTICE_EDIT("/admin/notices/edit"),

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
		map.put(AdminCommands.LOGIN.path, new LoginCommand());
		map.put(AdminCommands.INDEX.path, new AdminIndexCommand());
		map.put(AdminCommands.USER_MANAGEMENT.path, new ManagementCommand());
		map.put(AdminCommands.USER_DELETE.path, new DeleteCommand());
		map.put(AdminCommands.USER_RECOVERY.path, new RecoveryCommand());
		map.put(AdminCommands.NOTICE_MANAGEMENT.path, new AdminNoticeManagementCommand());
		map.put(AdminCommands.ARTICLE_DELETE.path, new com.bbs.command.admin.article.DeleteCommand());
		map.put(AdminCommands.ARTICLE_RECOVERY.path, new com.bbs.command.admin.article.RecoveryCommand());
		map.put(AdminCommands.NOTICE_INPUT.path, new InputCommand());
		map.put(AdminCommands.NOTICE_DETAIL.path, new DetailCommand());
		map.put(AdminCommands.NOTICE_EDIT.path, new EditCommand());
	}

	@Getter
	private static final Map<String, Long> boardIdMap = new HashMap<>();

	/**
	 * DB 게시판(board)테이블 PK 및 해당 경로 Map
	 */
	static {
		boardIdMap.put("notices", 1L);
		boardIdMap.put("community", 2L);
	}
}

