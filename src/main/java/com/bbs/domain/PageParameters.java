package com.bbs.domain;

import com.bbs.util.CommandUtil;
import java.util.ArrayList;
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


	public void setPaginationElements (String requestedPageNumber, int numberOfItems) {
		this.numberOfItems = numberOfItems;
		this.pageNumber = validatePageNumber(requestedPageNumber);
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
	 * 페이지 번호 유효성 검사
	 * 정수인 경우 대상에 맞게 반환, 아닌 경우 1 반환
	 *
	 * @param pageNumber 사용자 입력 페이지 번호
	 * @return
	 */
	private int validatePageNumber(String pageNumber) {
		if (CommandUtil.isPositiveInteger(pageNumber)) {
			return Integer.parseInt(pageNumber);
		}
		return 1;
	}

	/**
	 * 게시판 검색을 위한 초기 생성 빌더
	 *
	 * @param boardId 게시판 번호
	 * @param searchKeyword 검색 키워드
	 */
	@Builder
	public PageParameters(Long boardId, String searchKeyword) {
		this.boardId = boardId;
		this.searchKeyword = searchKeyword;
	}
}
