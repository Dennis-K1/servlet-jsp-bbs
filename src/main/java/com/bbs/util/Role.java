package com.bbs.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
	ADMIN(1L),
	USER(2L);

	private Long roleId;
}
