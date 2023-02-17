package com.bbs.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 각 권한 및 권한 PK
 */
public enum Role {

	ADMIN(1L),
	USER(2L);

	/**
	 * DB role table PK
	 */
	private Long roleId;
}
