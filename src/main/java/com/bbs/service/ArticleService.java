package com.bbs.service;

import com.bbs.dao.ArticleDAO;
import com.bbs.domain.Article;
import com.bbs.domain.PageParameters;
import java.util.List;

/**
 * 게시글 관련 서비스
 */
public class ArticleService {

	/**
	 * 검색 조건 기반 총 게시글 갯수 조회
	 *
	 * @param pageParameters 검색 조건
	 * @return
	 */
	public int getNumberOfArticlesBySearch(PageParameters pageParameters) {
		ArticleDAO articleDAO = new ArticleDAO();
		return articleDAO.getNumberOfArticlesBySearch(pageParameters);
	}

	/**
	 * 검색 조건 기반 게시글 리스트 조회
	 *
	 * @param pageParameters 검색 조건
	 * @return
	 */
	public List<Article> getArticleList(PageParameters pageParameters) {
		ArticleDAO articleDAO = new ArticleDAO();
		return articleDAO.getArticleList(pageParameters);
	}
}
