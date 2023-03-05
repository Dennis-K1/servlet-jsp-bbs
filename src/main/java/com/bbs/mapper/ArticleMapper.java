package com.bbs.mapper;

import com.bbs.domain.Article;
import com.bbs.domain.PageParameters;
import com.bbs.domain.Reply;
import com.bbs.domain.User;
import java.util.List;

/**
 * Article, Reply, NestedReply 관련 Mapper
 */
public interface ArticleMapper {

	/**
	 * 검색값 기반 총 게시글 수 조회
	 *
	 * @param pageParameters 검색값
	 */
	int getNumberOfArticlesBySearch(PageParameters pageParameters);

	/**
	 * 검색값 기반 게시글 목록 반환
	 *
	 * @param pageParameters 검색값
	 */
	List<Article> getArticleList(PageParameters pageParameters);

	/**
	 * PK에 해당하는 게시글 삭제 처리
	 *
	 * @param id 대상 게시글 PK
	 */
	int deleteArticleById(Long id);

	/**
	 * PK에 해당하는 게시글 삭제일 업데이트
	 *
	 * @param id 대상 게시글 PK
	 */
	int updateDateDeleted(Long id);

	/**
	 * PK에 해당하는 게시글 복구 처리
	 *
	 * @param id 대상 게시글 PK
	 */
	int recoverArticleById(Long id);

	/**
	 * PK에 해당하는 게시글 삭제일 Null 복구
	 *
	 * @param id 대상 게시글 PK
	 */
	int recoverDateDeleted(Long id);

	/**
	 * 게시글 등록
	 *
	 * @param article 게시글 정보 객체
	 * @return 수행 결과 및 삽입 후 해당 게시글 PK
	 */
	int inputArticle(Article article);

	/**
	 * 게시글 정보 조회
	 *
	 * @param id 대상 게시글 PK
	 */
	Article getArticleById(Long id);

	/**
	 * 게시글 조회수 증가
	 *
	 * @param id 대상 게시글 PK
	 */
	int increaseArticleViewsById(Long id);

	/**
	 * 게시글 수정
	 *
	 * @param article 대상 게시글 정보 객체
	 */
	int editArticle(Article article);

	/**
	 * 사용자가 등록한 게시글 목록 반환
	 *
	 * @param user 사용자 정보 객체
	 */
	List<Article> getArticleListByUser(User user);

	/**
	 * 게시글에 속한 답글(댓글) 목록 반환
	 *
	 * @param articleId 대상 게시글 PK
	 */
	List<Reply> getReplyListById(Long articleId);

	/**
	 * 답글(댓글) 등록
	 *
	 * @param reply 답글(댓글) 정보 객체
	 */
	int inputReply(Reply reply);

	/**
	 * 답글(댓글) 삭제 처리
	 *
	 * @param replyId 대상 답글(댓글) PK
	 */
	int deleteReplyById(Long replyId);

	/**
	 * 종속 게시판 이름 조회
	 *
	 * @param articleId 대상 게시글 PK
	 */
	Long getBoardIdById(Long articleId);

	/**
	 * 답글(댓글) 복구 처리
	 *
	 * @param replyId 대상 댓글 PK
	 */
	int recoverReplyById(Long replyId);
}
