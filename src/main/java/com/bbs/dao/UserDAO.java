package com.bbs.dao;


import com.bbs.domain.User;
import com.bbs.util.MybatisSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * 사용자 관련 DB 접근 객체
 */
public class UserDAO {

	/**
	 * 해당 아이디 사용자 정보 조회
	 * @param account 사용자 아이디
	 * @return 사용자 정보가 담긴 User 객체
	 */
	public User getUser(String account) {
		SqlSession session = getSqlSession();
		try {
			return session.selectOne("com.bbs.dao.UserDAO.getUser", account);
		} finally {
			session.close();
		}
	}

	/**
	 * DB 접근을 위한 SqlSession 생성
	 * @return SqlSession
	 */
	private SqlSession getSqlSession() {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		return sqlSessionFactory.openSession(true);
	}
}
