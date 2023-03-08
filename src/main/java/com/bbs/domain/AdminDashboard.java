package com.bbs.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * 어드민 인덱스 대시보드용 VO
 */
@Getter
@Setter
public class AdminDashboard {

	/**
	 * 활성 상태인 공지사항 게시글 수
	 */
	private Integer numberOfActiveNoticeArticles;

	/**
	 * 활성 상태인 자유게시판 게시글 수
	 */
	private Integer numberOfActiveCommunityArticles;

	/**
	 * 활성 상태인 1:1문의 게시글 수
	 */
	private Integer numberOfActiveInquiryArticles;

	/**
	 * 활성 상태인 갤러리 게시글 수
	 */
	private Integer numberOfActiveGalleryArticles;

	/**
	 * 활성 유저 수
	 */
	private Integer numberOfActiveUsers;

	/**
	 * 답변 필요 1:1문의 수
	 */
	private Integer numberOfPendingInquiries;

	/**
	 * 조회수 top5 게시글 목록
	 */
	private List<Article> top5ViewsArticles;

	/**
	 * 최근 등록 게시물 목록 (각 게시판당 3개)
	 */
	private List<Article> top3RecentArticlesByEachBoard;

}
