package com.bbs.service;

import com.bbs.config.MybatisSqlSessionFactory;
import com.bbs.mapper.ArticleMapper;
import com.bbs.mapper.UserMapper;
import com.bbs.domain.Article;
import com.bbs.domain.PageParameters;
import com.bbs.domain.User;
import java.util.List;
import java.util.Objects;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * 게시글 관련 서비스
 */
public class ArticleService {

	/**
	 * 마이바티스 및 매퍼 실행을 위한 SqlSessionFactory
	 */
	private SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();

	/**
	 * 검색 조건 기반 총 게시글 갯수 조회
	 *
	 * @param pageParameters 검색 조건
	 * @return
	 */
	public int getNumberOfArticlesBySearch(PageParameters pageParameters) {
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			ArticleMapper articleMapper = session.getMapper(ArticleMapper.class);
			return articleMapper.getNumberOfArticlesBySearch(pageParameters);
		}
	}

	/**
	 * 검색 조건 기반 게시글 리스트 조회
	 *
	 * @param pageParameters 검색 조건
	 * @return
	 */
	public List<Article> getArticleList(PageParameters pageParameters) {
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			ArticleMapper articleMapper = session.getMapper(ArticleMapper.class);
			return articleMapper.getArticleList(pageParameters);
		}
	}

	/**
	 * 대상 게시글 삭제 (삭제 처리, 삭제일 업데이트)
	 *
	 * @param id 대상 게시글 번호
	 * @return
	 */
	public int deleteArticleById(Long id) {
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			ArticleMapper articleMapper = session.getMapper(ArticleMapper.class);
			if (articleMapper.deleteArticleById(id) == 1) {
				return articleMapper.updateDateDeleted(id);
			}
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
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			ArticleMapper articleMapper = session.getMapper(ArticleMapper.class);
			if (articleMapper.recoverArticleById(id) == 1) {
				return articleMapper.recoverDateDeleted(id);
			}
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
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			ArticleMapper articleMapper = session.getMapper(ArticleMapper.class);
			UserMapper userMapper = session.getMapper(UserMapper.class);

			User user = userMapper.getUserByAccount(article.getAccount());
			article.setUserId(user.getId());
			return articleMapper.inputArticle(article);
		}
	}

	/**
	 * 게시글 조회
	 *
	 * @param id 대상 게시글 번호
	 * @return
	 */
	public Article getArticleById(Long id) {
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			ArticleMapper articleMapper = session.getMapper(ArticleMapper.class);
			Article article = articleMapper.getArticleById(id);
			if (Objects.equals(null, article)) {
				throw new RuntimeException();
			}
			return article;
		}
	}

	/**
	 * 게시글 조회수 증가
	 *
	 * @param id 대상 게시글
	 * @return
	 */
	public int increaseArticleViewsById(Long id) {
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			ArticleMapper articleMapper = session.getMapper(ArticleMapper.class);
			return articleMapper.increaseArticleViewsById(id);
		}
	}

	public int editArticle(Article article) {
		try (SqlSession session = sqlSessionFactory.openSession(true)){
			ArticleMapper articleMapper = session.getMapper(ArticleMapper.class);
			return articleMapper.editArticle(article);
		}
	}
}
