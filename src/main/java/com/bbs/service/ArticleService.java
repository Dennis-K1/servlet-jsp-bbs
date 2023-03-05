package com.bbs.service;

import com.bbs.config.MybatisSqlSessionFactory;
import com.bbs.domain.Reply;
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
 * 게시글,답글(댓글) 관련 서비스
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
			Article article = articleMapper.getArticleById(id);

			UserMapper userMapper = session.getMapper(UserMapper.class);
			userMapper.decreaseArticleCountById(article.getUser().getId());
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
	 * 게시글 등록 (회원 아이디 조회, 게시글 등록) -- 등록 시 유저 count_article (게시 게시글 수) 증가
	 *
	 * @param article 게시글 정보 객체
	 * @return
	 */
	public int inputArticle(Article article) {
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			ArticleMapper articleMapper = session.getMapper(ArticleMapper.class);
			UserMapper userMapper = session.getMapper(UserMapper.class);

			User user = userMapper.getUserByAccount(article.getUser().getAccount());
			userMapper.increaseArticleCountById(user.getId());

			article.setUser(user);
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

	/**
	 * 게시글 수정
	 *
	 * @param article 수정할 정보 및 대상 게시글 번호가 든 객체
	 * @return
	 */
	public int editArticle(Article article) {
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			ArticleMapper articleMapper = session.getMapper(ArticleMapper.class);
			return articleMapper.editArticle(article);
		}
	}

	/**
	 * 대상 사용자가 작성한 게시글 목록 반환
	 *
	 * @param user 대상 사용자 정보 객체
	 * @return
	 */
	public List<Article> getArticleListByUser(User user) {
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			ArticleMapper articleMapper = session.getMapper(ArticleMapper.class);
			return articleMapper.getArticleListByUser(user);
		}
	}

	/**
	 * 답글(댓글) 등록
	 *
	 * @param reply 답글(댓글) 정보 객체
	 * @return
	 */
	public int inputReply(Reply reply) {
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			ArticleMapper articleMapper = session.getMapper(ArticleMapper.class);
			UserMapper userMapper = session.getMapper(UserMapper.class);

			User user = userMapper.getUserByAccount(reply.getUser().getAccount());
			reply.setUser(user);
			return articleMapper.inputReply(reply);
		}
	}

	/**
	 * 대상 답글(댓글) 삭제
	 *
	 * @param replyId 대상 답글(댓글) 번호
	 * @return
	 */
	public int deleteReplyById(Long replyId) {
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			ArticleMapper articleMapper = session.getMapper(ArticleMapper.class);
			return articleMapper.deleteReplyById(replyId);
		}
	}

	/**
	 * 게시글 번호로 게시판 번호 조회
	 *
	 * @param articleId 대상 게시글 번호
	 * @return
	 */
	public Long getBoardIdById(Long articleId) {
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			ArticleMapper articleMapper = session.getMapper(ArticleMapper.class);
			return articleMapper.getBoardIdById(articleId);
		}
	}

	/**
	 * 답글(댓글) 복구
	 *
	 * @param replyId 대상 답글(댓글) 번호
	 * @return
	 */
	public int recoverReplyById(Long replyId) {
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			ArticleMapper articleMapper = session.getMapper(ArticleMapper.class);
			return articleMapper.recoverReplyById(replyId);
		}
	}
}
