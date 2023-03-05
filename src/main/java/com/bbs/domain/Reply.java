package com.bbs.domain;

import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 답글(댓글) 정보
 */
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
	 * 대댓글 등록 대상 댓글 PK
	 */
	@Setter
	private Long replyId;

	/**
	 * 작성일
	 */
	private Date dateRegistered;

	/**
	 * 답글(댓글) 삭제 여부
	 */
	private Byte replyDeleted;

	/**
	 * 대댓글 목록
	 */
	@Setter
	private List<NestedReply> nestedReplyList;

	@Builder
	public Reply(Long articleId, User user, String content) {
		this.articleId = articleId;
		this.user = user;
		this.content = content;
	}
}
