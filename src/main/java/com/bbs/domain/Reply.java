package com.bbs.domain;

import java.util.Date;
import java.util.List;
import lombok.Builder;
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

	private Byte replyDeleted;

	@Setter
	private List<NestedReply> nestedReplyList;

	@Builder
	public Reply(Long articleId, User user, String content) {
		this.articleId = articleId;
		this.user = user;
		this.content = content;
	}
}
