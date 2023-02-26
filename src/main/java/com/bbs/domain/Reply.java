package com.bbs.domain;

import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class Reply {

	/**
	 * reply 테이블 PK
	 */
	@Setter
	private Long id;

	/**
	 * 종속 게시글 PK
	 */
	private Long articleId;

	/**
	 * 작성자 정보
	 */
	@Setter
	private User user;

	/**
	 * 내용
	 */
	private String content;

	/**
	 * 작성일
	 */
	private Date dateRegistered;
}
