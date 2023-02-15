package com.bbs.dao;

import com.bbs.domain.Article;
import com.bbs.domain.PageParameters;
import com.bbs.util.MybatisSqlSessionFactory;
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
		SqlSession session = sqlSessionFactory.openSession(true);
		try{
			return session.selectOne(ARTICLE_MAPPER + "getNumberOfArticlesBySearch", pageParameters);
		} finally {
			session.close();
		}
	}

	/**
	 * 검색 조건 기반 게시글 리스트 반환
	 *
	 * @param pageParameters 검색 조건
	 * @return
	 */
	public List<Article> getArticleList(PageParameters pageParameters) {
		SqlSession session = sqlSessionFactory.openSession(true);
		try{
			return session.selectList(ARTICLE_MAPPER + "getArticleList", pageParameters);
		} finally {
			session.close();
		}
	}
}
