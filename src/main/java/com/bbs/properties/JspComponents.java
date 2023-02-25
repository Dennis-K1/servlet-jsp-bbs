package com.bbs.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * JSP 유틸 페이지 모음
 */
@Getter
@AllArgsConstructor
public enum JspComponents {
	SIDE_MENU("/WEB-INF/views/admin/util/sideMenu.jsp"),
	TOP_NAV("/WEB-INF/views/admin/util/topNav.jsp"),
	PAGINATION("/WEB-INF/views/admin/util/pagination.jsp"),
	SEARCH_BAR("/WEB-INF/views/admin/util/searchBar.jsp"),
	ERROR_MESSAGE("/WEB-INF/views/admin/util/errorMessage.jsp");

	String path;
}
