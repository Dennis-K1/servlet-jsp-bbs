package com.bbs.dao;


import com.bbs.domain.User;
import com.bbs.util.MybatisSqlSessionFactory;
import java.sql.SQLException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * 사용자 관련 DB 접근 객체
 */
public class UserDAO {

	private final String USER_MAPPER = "com.bbs.dao.UserDAO.";
	/**
	 * 해당 아이디 사용자 정보 조회
	 * @param account 사용자 아이디
	 * @return 사용자 정보가 담긴 User 객체
	 */
	public User getUser(String account) {
		SqlSession session = getSqlSession();
		try {
			return session.selectOne(USER_MAPPER + "getUser", account);
		} finally {
			session.close();
		}
	}

	public String getRoleName(Long roleId) {
		SqlSession session = getSqlSession();
		try {
			return session.selectOne(USER_MAPPER + "getRoleName", roleId);
		} finally {
			session.close();
		}
	}
	public int registerUser(User user) {
		SqlSession session = getSqlSession();
		try {
			return session.insert(USER_MAPPER + "registerUser", user);
		} finally {
			session.close();
		}
	}
	public String getUserPassword(String account) {
		SqlSession session = getSqlSession();
		try {
			return session.selectOne(USER_MAPPER + "getUserPassword", account);
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
