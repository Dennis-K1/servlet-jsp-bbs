package com.bbs.mapper;

import com.bbs.domain.PageParameters;
import com.bbs.domain.User;
import java.util.List;

/**
 * User 관련 Mapper
 */
public interface UserMapper {

	/**
	 * 권한명 조회
	 *
	 * @param roleId 대상 PK
	 * @return 권한명
	 */
	String getRoleName(Long roleId);

	/**
	 * 사용자 등록
	 *
	 * @param user 사용자 정보 객체
	 */
	int registerUser(User user);

	/**
	 * 아이디로 사용자 정보 조회
	 *
	 * @param account 아이디
	 * @return 사용자 정보 객체
	 */
	User getUserByAccount(String account);

	/**
	 * PK로 사용자 정보 조회
	 *
	 * @param id user 테이블 PK
	 * @return 사용자 정보 객체
	 */
	User getUserById(Long id);

	/**
	 * 사용자 목록 조회
	 *
	 * @param pageParameters 검색값
	 */
	List<User> getUserList(PageParameters pageParameters);

	/**
	 * 검색값 기반 총 사용자 수 조회
	 *
	 * @param pageParameters 검색값
	 */
	int getNumberOfUsersBySearch(PageParameters pageParameters);

	/**
	 * PK로 사용자 삭제 처리
	 *
	 * @param id 대상 사용자 PK
	 */
	int deleteUserById(int id);

	/**
	 * 삭제일 업데이트
	 *
	 * @param id 대상 사용자 PK
	 */
	int updateDateDeleted(int id);

	/**
	 * PK로 사용자 복구 처리
	 *
	 * @param id 대상 사용자 PK
	 */
	int recoverUserById(int id);

	/**
	 * 삭제일 Null 복구
	 *
	 * @param id 대상 사용자 PK
	 */
	int recoverDateDeleted(int id);

	/**
	 * 접속횟수 증가
	 *
	 * @param user 대상 사용자 정보 객체
	 */
	int increaseVisitCount(User user);

	/**
	 * 마지막 접속일 업데이트
	 *
	 * @param user 대상 사용자 정보 객체
	 */
	int updateLastLogin(User user);

	/**
	 * 사용자 등록 게시글 수 증가
	 *
	 * @param id 대상 사용자 PK
	 */
	int increaseArticleCountById(Long id);

	/**
	 * 사용자 등록 게시글 수 감소
	 *
	 * @param id 대상 사용자 PK
	 */
	int decreaseArticleCountById(Long id);
}
