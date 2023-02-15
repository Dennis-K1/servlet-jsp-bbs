package com.bbs.domain;

import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;


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

}
