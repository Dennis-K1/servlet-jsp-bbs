package com.bbs.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * JSP 유틸 컴포넌트 명칭 및 경로 모음
 */
@Getter
@AllArgsConstructor
public enum JspComponents {

	// 커맨드별 화면 컴포넌트 모음

	/**
	 * 공지사항 목록
	 */
	ADMIN_NOTICE_MANAGEMENT("/WEB-INF/views/admin/articles/notice/management.jsp"),

	/**
	 * 공지사항 상세
	 */
	ADMIN_NOTICE_DETAIL("/WEB-INF/views/admin/articles/notice/detail.jsp"),

	/**
	 * 공지사항 등록
	 */
	ADMIN_NOTICE_INPUT_FORM("/WEB-INF/views/admin/articles/notice/inputForm.jsp"),

	/**
	 * 공지사항 수정
	 */
	ADMIN_NOTICE_EDIT_FORM("/WEB-INF/views/admin/articles/notice/editForm.jsp"),

	/**
	 * 자유게시판 목록
	 */
	ADMIN_COMMUNITY_MANAGEMENT("/WEB-INF/views/admin/articles/community/management.jsp"),

	/**
	 * 자유게시판 상세
	 */
	ADMIN_COMMUNITY_DETAIL("/WEB-INF/views/admin/articles/community/detail.jsp"),

	/**
	 * 1:1문의 목록
	 */
	ADMIN_INQUIRY_MANAGEMENT("/WEB-INF/views/admin/articles/inquiry/management.jsp"),

	/**
	 * 1:1문의 상세
	 */
	ADMIN_INQUIRY_DETAIL("/WEB-INF/views/admin/articles/inquiry/detail.jsp"),


	// 전체 유틸 컴포넌트 모음

	/**
	 * 사이드 메뉴
	 */
	SIDE_MENU("/WEB-INF/views/admin/util/sideMenu.jsp"),

	/**
	 * 탑 네비게이션
	 */
	TOP_NAV("/WEB-INF/views/admin/util/topNav.jsp"),

	/**
	 * 목록 페지네이션 컴포넌트
	 */
	PAGINATION("/WEB-INF/views/admin/util/pagination.jsp"),

	/**
	 * 목록 검색 컴포넌트
	 */
	SEARCH_BAR("/WEB-INF/views/admin/util/searchBar.jsp"),

	/**
	 * 에러메세지 동작 컴포넌트
	 */
	ERROR_MESSAGE("/WEB-INF/views/admin/util/errorMessage.jsp");

	String path;
}

