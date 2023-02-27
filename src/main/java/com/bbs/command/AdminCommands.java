package com.bbs.command;

import com.bbs.command.admin.LoginFormCommand;
import com.bbs.command.admin.LogoutCommand;
import com.bbs.command.admin.article.EditCommand;
import com.bbs.command.admin.article.EditFormCommand;
import com.bbs.command.admin.article.DetailCommand;
import com.bbs.command.admin.AdminIndexCommand;
import com.bbs.command.admin.LoginCommand;
import com.bbs.command.admin.article.ManagementCommand;
import com.bbs.command.admin.article.InputFormCommand;
import com.bbs.command.admin.reply.InputCommand;
import com.bbs.command.admin.user.DeleteCommand;
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
	 * 메인페이지
	 */
	INDEX("/admin"),

	/**
	 * 로그인
	 */
	LOGIN("/admin/login"),

	/**
	 * 로그아웃
	 */
	LOGOUT("/admin/logout"),

	/**
	 * 로그인 화면
	 */
	LOGIN_FORM("/admin/loginForm"),

	/**
	 * 유저 관리
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
	 * 유저 상세페이지
	 */
	USER_DETAIL("/admin/users/detail"),

	/**
	 * (공통) 게시글 목록 관리
	 */
	ARTICLE_MANAGEMENT("/admin/articles/management"),

	/**
	 * (공통) 게시글 삭제
	 */
	ARTICLE_DELETE("/admin/articles/delete"),

	/**
	 * (공통) 게시글 복구 처리
	 */
	ARTICLE_RECOVERY("/admin/articles/recovery"),

	/**
	 * (공통) 게시글 업로드
	 */
	ARTICLE_INPUT("/admin/articles/input"),

	/**
	 * (공통) 게시글 작성 화면 게시판에 맞추어 반환)
	 */
	ARTICLE_INPUT_FORM("/admin/articles/inputForm"),

	/**
	 * (공통) 게시글 상세 페이지 (게시판에 맞추어 반환)
	 */
	ARTICLE_DETAIL("/admin/articles/detail"),

	/**
	 * (공통) 게시글 수정
	 */
	ARTICLE_EDIT("/admin/articles/edit"),

	/**
	 * (공통) 게시글 수정 화면 (게시판에 맞추어 반환)
	 */
	ARTICLE_EDIT_FORM("/admin/articles/editForm"),

	/**
	 * 공지사항 등록
	 */
	NOTICE_INPUT("/admin/notices/input"),

	/**
	 * 공지사항 작성 화면 (articles/inputForm 공통 커맨드에서 해당하는 View 로 forward)
	 */
	NOTICE_INPUT_FORM("/admin/notices/inputForm"),

	/**
	 * 공지사항 관리 (admin/articles/management 공통 커맨드에서 해당하는 View 로 forward)
	 */
	NOTICE_MANAGEMENT("/admin/notices"),

	/**
	 * 공지사항 상세페이지 (articles/detail 공통 커맨드에서 해당하는 View 로 forward)
	 */
	NOTICE_DETAIL("/admin/notices/detail"),

	/**
	 * 공지사항 수정 등록
	 */
	NOTICE_EDIT("/admin/notices/edit"),

	/**
	 * 공지사항 수정 화면 (articles/editForm 공통 커맨드에서 해당하는 View 로 forward)
	 */
	NOTICE_EDIT_FORM("/admin/notices/editForm"),

	/**
	 * 1:1 문의 관리 (admin/articles/management 공통 커맨드에서 해당하는 View 로 forward)
	 */
	INQUIRY_MANAGEMENT("/admin/inquiries"),

	/**
	 * 1:1 문의 상세페이지 (articles/detail 공통 커맨드에서 해당하는 View 로 forward)
	 */
	INQUIRY_DETAIL("/admin/inquiries/detail"),

	/**
	 * 1:1 문의 답글 등록
	 */
	INQUIRY_REPLY("/admin/inquiries/reply"),
	REPLY_DELETE("/admin/replies/delete"),
	REPLY_RECOVERY("/admin/replies/recovery"),

	/**
	 * 자유게시판 관리 (admin/articles/management 공통 커맨드에서 해당하는 View 로 forward)
	 */
	COMMUNITY_MANAGEMENT("/admin/community"),

	/**
	 * 자유게시판 상세페이지 (admin/articles/detail 공통 커맨드에서 해당하는 View 로 forward)
	 */
	COMMUNITY_DETAIL("/admin/community/detail"),


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
		map.put(AdminCommands.INDEX.path, new AdminIndexCommand());
		map.put(AdminCommands.LOGIN.path, new LoginCommand());
		map.put(AdminCommands.LOGIN_FORM.path, new LoginFormCommand());
		map.put(AdminCommands.LOGOUT.path, new LogoutCommand());
		map.put(AdminCommands.USER_MANAGEMENT.path, new com.bbs.command.admin.user.ManagementCommand());
		map.put(AdminCommands.USER_DETAIL.path, new com.bbs.command.admin.user.DetailCommand());
		map.put(AdminCommands.USER_DELETE.path, new DeleteCommand());
		map.put(AdminCommands.USER_RECOVERY.path, new RecoveryCommand());
		map.put(AdminCommands.NOTICE_MANAGEMENT.path, new ManagementCommand());
		map.put(AdminCommands.ARTICLE_DELETE.path, new com.bbs.command.admin.article.DeleteCommand());
		map.put(AdminCommands.ARTICLE_RECOVERY.path, new com.bbs.command.admin.article.RecoveryCommand());
		map.put(AdminCommands.NOTICE_INPUT.path, new com.bbs.command.admin.article.InputCommand());
		map.put(AdminCommands.NOTICE_INPUT_FORM.path, new InputFormCommand());
		map.put(AdminCommands.NOTICE_DETAIL.path, new DetailCommand());
		map.put(AdminCommands.ARTICLE_EDIT.path, new EditCommand());
		map.put(AdminCommands.NOTICE_EDIT_FORM.path, new EditFormCommand());
		map.put(AdminCommands.INQUIRY_MANAGEMENT.path, new ManagementCommand());
		map.put(AdminCommands.INQUIRY_DETAIL.path, new DetailCommand());
		map.put(AdminCommands.INQUIRY_REPLY.path, new InputCommand());
		map.put(AdminCommands.COMMUNITY_MANAGEMENT.path, new ManagementCommand());
		map.put(AdminCommands.COMMUNITY_DETAIL.path, new DetailCommand());
		map.put(AdminCommands.REPLY_DELETE.path, new com.bbs.command.admin.reply.DeleteCommand());
		map.put(AdminCommands.REPLY_RECOVERY.path, new com.bbs.command.admin.reply.RecoveryCommand());
	}

	@Getter
	private static final Map<String, Long> boardIdMap = new HashMap<>();

	/**
	 * DB 게시판(board)테이블 PK 및 해당 경로 Map
	 */
	static {
		boardIdMap.put("/admin/notices", 1L);
		boardIdMap.put("/admin/community", 2L);
		boardIdMap.put("/admin/inquiries", 3L);
	}
}

