package com.bbs.domain;

import com.bbs.util.CommandUtil;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Pagination {

	private int pageNumberOffset;

	private int pageNumber;

	private int numberOfItems;

	private final int PAGE_SIZE = 10;

	private int pageCount;

	private int startPage;

	private int endPage;

	private List<Integer> displayedPageNumbers = new ArrayList<>();


	public Pagination (String requestedPageNumber, int numberOfItems) {
		this.numberOfItems = numberOfItems;
		this.pageNumber = validatePageNumber(requestedPageNumber);
		this.pageNumberOffset = getOffset(this.pageNumber);
		this.pageCount = Math.round(numberOfItems / PAGE_SIZE) + 1;
		this.startPage = (this.pageNumber / 10) * 10 + 1;
		this.endPage = (this.pageNumber / 10) * 10 + 10;
		if (this.endPage > pageCount) {
			this.endPage = pageCount;
		}
		for (int page = startPage; page <= endPage; page++) {
			displayedPageNumbers.add(page);
		}
	}
	private int getOffset(int pageNumber) {
		return (pageNumber - 1) * 10;
	}

	private int validatePageNumber(String pageNumber) {
		if (CommandUtil.isPositiveInteger(pageNumber)) {
			return Integer.parseInt(pageNumber);
		}
		return 1;
	}
}
