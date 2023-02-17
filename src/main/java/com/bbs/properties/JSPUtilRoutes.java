package com.bbs.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * JSP 유틸 페이지 모음
 */
@Getter
@AllArgsConstructor
public enum JSPUtilRoutes {
	ERROR_MESSAGE("/WEB-INF/views/util/errorMessage.jsp");

	String path;
}
