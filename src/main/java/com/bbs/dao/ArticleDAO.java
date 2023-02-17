package com.bbs.dao;

import com.bbs.domain.Article;
import com.bbs.domain.PageParameters;
import com.bbs.config.MybatisSqlSessionFactory;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * 게시글 관련 DB 접근 객체
 */
public class ArticleDAO {

	private SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();

	/**
	 * article-mapper namespace
	 */
	private final String ARTICLE_MAPPER = "com.bbs.dao.ArticleDAO.";

	/**
	 * 검색 조건 기반 총 게시글 수 반환
	 *
	 * @param pageParameters 검색 조건
	 * @return
	 */
	public int getNumberOfArticlesBySearch(PageParameters pageParameters) {
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			return session.selectOne(ARTICLE_MAPPER + "getNumberOfArticlesBySearch",
				pageParameters);
		}
	}

	/**
	 * 검색 조건 기반 게시글 리스트 반환
	 *
	 * @param pageParameters 검색 조건
	 * @return
	 */
	public List<Article> getArticleList(PageParameters pageParameters) {
		try (SqlSession session = sqlSessionFactory.openSession(true)){
			return session.selectList(ARTICLE_MAPPER + "getArticleList", pageParameters);
		}
	}

	/**
	 * 대상 게시글 삭제 처리
	 *
	 * @param id 게시글 번호
	 * @return
	 */
	public int deleteArticleById(Long id) {
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			return session.update(ARTICLE_MAPPER + "deleteArticleById", id);
		}
	}


	/**
	 * 삭제일 갱신
	 *
	 * @param id 대상 게시글
	 * @return
	 */
	public int updateDateDeleted(Long id) {
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			return session.update(ARTICLE_MAPPER + "updateDateDeleted", id);
		}
	}

	/**
	 * 게시글 복구
	 *
	 * @param id 대상 게시글
	 * @return
	 */
	public int recoverArticleById(Long id) {
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			return session.update(ARTICLE_MAPPER + "recoverArticleById", id);
		}
	}

	/**
	 * 삭제일 NULL 복구
	 *
	 * @param id 대상 게시글
	 * @return
	 */
	public int recoverDateDeleted(Long id) {
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			return session.update(ARTICLE_MAPPER + "recoverDateDeleted", id);
		}
	}

	/**
	 * 게시글 등록
	 *
	 * @param article 게시글 정보 객체
	 * @return
	 */
	public int inputArticle(Article article) {
		try (SqlSession session = sqlSessionFactory.openSession(true)){
			return session.insert(ARTICLE_MAPPER + "inputArticle", article);
		}
	}

	/**
	 * PK로 게시글 조회
	 *
	 * @param id 게시글 번호
	 * @return
	 */
	public Article getArticleById(Long id) {
		try (SqlSession session = sqlSessionFactory.openSession(true)){
			return session.selectOne(ARTICLE_MAPPER + "getArticleById", id);
		}
	}

	/**
	 * 게시글 조회수 증가
	 *
	 * @param id 대상 게시글
	 * @return
	 */
	public int increaseArticleViewsById(Long id) {
		try (SqlSession session = sqlSessionFactory.openSession(true)){
			return session.update(ARTICLE_MAPPER + "increaseArticleViewsById", id);
		}
	}

	/**
	 * 파일 첨부 여부 업데이트
	 *
	 * @param id 대상 게시글 PK
	 * @return
	 */
	public int updateFileAttachedByArticleId(Long id) {
		try (SqlSession session = sqlSessionFactory.openSession(true)){
			return session.update(ARTICLE_MAPPER + "updateFileAttachedByArticleId", id);
		}
	}
}
