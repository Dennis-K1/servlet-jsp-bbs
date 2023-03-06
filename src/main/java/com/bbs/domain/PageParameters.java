package com.bbs.domain;

import com.bbs.util.CommandUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 페이지네이션, 검색 parameter 정보가 담긴 객체
 */
@Getter
@NoArgsConstructor
public class PageParameters {

	/**
	 * 게시판 번호
	 */
	private Long boardId;

	/**
	 * 검색 키워드
	 */
	private String searchKeyword;

	/**
	 * 페이지 번호 기반 DB query 용 offset
	 */
	private int pageNumberOffset;

	/**
	 * 조회 대상 페이지 번호
	 */
	private int pageNumber;

	/**
	 * 검색 범위 시작일
	 */
	private String startDate;

	/**
	 * 검색 범위 종료일
	 */
	private String endDate;

	/**
	 * 검색 카테고리 번호
	 */
	private String searchCategory;

	/**
	 * 검색 조건 기반 총 item 수
	 */
	private int numberOfItems;

	/**
	 * 리스트 표시 item 수
	 */
	private final int PAGE_SIZE = 10;

	/**
	 * 총 item 수 기반 표시되어야 할 페이지 갯수
	 */
	private int pageCount;

	/**
	 * 시작 페이지
	 */
	private int startPage;

	/**
	 * 끝 페이지
	 */
	private int endPage;

	/**
	 * 화면에 표시될 페이지 번호 리스트
	 */
	private List<Integer> displayedPageNumbers = new ArrayList<>();


	/**
	 * '사용자 요청 페이지 번호' 및 '검색값 기반 총 아이템 수'를 이용하여 페이지네이션 관련 변수 초기화
	 *
	 * @param numberOfItems 검색값 기반 총 아이템 수
	 */
	public void setPaginationElements (int numberOfItems) {
		this.numberOfItems = numberOfItems;
		this.pageNumberOffset = getOffset(this.pageNumber);
		this.pageCount = (int) (Math.ceil((double) numberOfItems / PAGE_SIZE));
		this.startPage = (this.pageNumber / 10) * 10 + 1;
		this.endPage = (this.pageNumber / 10) * 10 + 10;
		if (this.endPage > pageCount) {
			this.endPage = pageCount;
		}
		for (int page = startPage; page <= endPage; page++) {
			displayedPageNumbers.add(page);
		}
	}

	/**
	 * 페이지 번호에서 offset 추출
	 *
	 * @param pageNumber 조회 대상 페이지 번호
	 * @return
	 */
	private int getOffset(int pageNumber) {
		return (pageNumber - 1) * 10;
	}

	/**
	 * 게시판 검색을 위한 초기 생성 빌더
	 *
	 * @param boardId 게시판 번호
	 * @param searchKeyword 검색 키워드
	 */
	@Builder
	public PageParameters(Long boardId, String searchKeyword, String startDate, String endDate, String searchCategory, int pageNumber) {
		this.boardId = boardId;
		this.searchKeyword = searchKeyword;
		this.startDate = startDate;
		this.endDate = endDate;
		this.searchCategory = searchCategory;
		this.pageNumber = pageNumber;
	}
}
