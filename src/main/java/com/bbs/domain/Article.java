package com.bbs.domain;

import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 게시글 정보 객체
 * DB
 * 	  PK 		: 	Long 타입
 *    TINYINT	:	BYTE 타입
 *    DATE		:   java.util.Date 타입
 */
@Getter
@NoArgsConstructor
public class Article {

	/**
	 * 게시글 PK
	 */
	@Setter
	private Long id;

	/**
	 * 게시판 종류 테이블 PK
	 */
	private Long boardId;

	/**
	 * 게시판 이름
	 */
	private String boardName;

	/**
	 * 사용자 테이블 PK
	 */
	@Setter
	private Long userId;

	/**
	 * 게시글 제목
	 */
	private String title;

	/**
	 * 게시글 내용
	 */
	private String content;

	/**
	 * 게시글 등록일
	 */
	private Date dateRegistered;

	/**
	 * 게시글 수정일
	 */
	private Date lastUpdated;

	/**
	 * 게시글 삭제 여부
	 */
	private Byte articleDeleted;

	/**
	 * 게시글 삭제일
	 */
	private Date dateDeleted;

	/**
	 * 파일 첨부 여부
	 */
	@Setter
	private Boolean fileAttached;

	/**
	 * 게시글 조회수
	 */
	private Integer Views;


	/**
	 * 게시글 비밀번호
	 */
	private String password;

	/**
	 * 게시글 이미지 인코딩
	 */
	@Setter
	private String image;

	/**
	 * 작성자 정보
	 */
	@Setter
	private User user;

	/**
	 * 답글(댓글) 목록
	 */
	@Setter
	private List<Reply> replyList;

	/**
	 * 게시글 등록 객체를 위한 빌더
	 * @param boardId 게시판 번호
	 * @param title 게시글 제목
	 * @param content 게시글
	 * @param user 유저 정보
	 */
	@Builder
	public Article(Long id, Long boardId, String title, String content, User user) {
		this.id = id;
		this.boardId = boardId;
		this.title = title;
		this.content = content;
		this.user = user;
	}
}
