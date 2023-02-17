package com.bbs.service;

import com.bbs.dao.ArticleDAO;
import com.bbs.dao.UserDAO;
import com.bbs.domain.Article;
import com.bbs.domain.PageParameters;
import com.bbs.domain.User;
import java.util.List;
import java.util.Objects;

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

	/**
	 * 대상 게시글 삭제 (삭제 처리, 삭제일 업데이트)
	 *
	 * @param id 대상 게시글 번호
	 * @return
	 */
	public int deleteArticleById(Long id) {
		ArticleDAO articleDAO = new ArticleDAO();
		if (articleDAO.deleteArticleById(id) == 1){
			return articleDAO.updateDateDeleted(id);
		}
		return 0;
	}

	/**
	 * 대상 게시글 복구 처리 (복구 처리, 삭제일 업데이트)
	 *
	 * @param id 대상 게시글 번호
	 * @return
	 */
	public int recoverArticleById(Long id) {
		ArticleDAO articleDAO = new ArticleDAO();
		if (articleDAO.recoverArticleById(id) == 1){
			return articleDAO.recoverDateDeleted(id);
		}
		return 0;
	}

	/**
	 * 게시글 등록 (회원 아이디 조회, 게시글 등록)
	 *
	 * @param article 게시글 정보 객체
	 * @return
	 */
	public int inputArticle(Article article) {
		ArticleDAO articleDAO = new ArticleDAO();
		UserDAO userDAO = new UserDAO();
		User user = userDAO.getUserByAccount(article.getAccount());
		article.setUserId(user.getId());
		return articleDAO.inputArticle(article);
	}

	/**
	 * 게시글 조회
	 *
	 * @param id 대상 게시글 번호
	 * @return
	 */
	public Article getArticleById(Long id) {
		ArticleDAO articleDAO = new ArticleDAO();
		Article article = articleDAO.getArticleById(id);
		if (Objects.equals(null, article)){
			throw new RuntimeException();
		}
		return article;
	}

	/**
	 * 게시글 조회수 증가
	 *
	 * @param id 대상 게시글
	 * @return
	 */
	public int increaseArticleViewsById(Long id) {
		ArticleDAO articleDAO = new ArticleDAO();
		return articleDAO.increaseArticleViewsById(id);
	}
}
