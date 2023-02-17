package com.bbs.domain;

import java.util.Date;
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
	private Long id;

	/**
	 * 게시판 종류 테이블 PK
	 */
	private Long boardId;

	/**
	 * 사용자 테이블 PK
	 */
	@Setter
	private Long userId;

	/**
	 * 사용자 아이디
	 */
	private String account;
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
	private Byte fileAttached;

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
	 * 게시글 등록 객체를 위한 빌더
	 * @param boardId 게시판 번호
	 * @param userId 유저 번호
	 * @param account 유저 아아디
	 * @param title 게시글 제목
	 * @param content 게시글 내용
	 */
	@Builder
	public Article(Long boardId, Long userId, String account, String title, String content) {
		this.boardId = boardId;
		this.userId = userId;
		this.account = account;
		this.title = title;
		this.content = content;
	}
}
