package com.bbs.dao;


import com.bbs.domain.User;
import com.bbs.util.MybatisSqlSessionFactory;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * 사용자 관련 DB 접근 객체
 */
public class UserDAO {

	/**
	 * DB 접근을 위한 SqlSession 생성
	 * @return SqlSession
	 */
	private SqlSession getSqlSession() {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		return sqlSessionFactory.openSession(true);
	}

	/**
	 * user-mapper namespace
	 */
	private final String USER_MAPPER = "com.bbs.dao.UserDAO.";

	/**
	 * 권한명 조회
	 *
	 * @param roleId role table PK
	 * @return 권한명
	 */
	public String getRoleName(Long roleId) {
		SqlSession session = getSqlSession();
		try {
			return session.selectOne(USER_MAPPER + "getRoleName", roleId);
		} finally {
			session.close();
		}
	}

	/**
	 * 사용자 등록
	 *
	 * @param user 사용자 객체
	 * @return 수행 결과
	 */
	public int registerUser(User user) {
		SqlSession session = getSqlSession();
		try {
			return session.insert(USER_MAPPER + "registerUser", user);
		} finally {
			session.close();
		}
	}

	/**
	 * 사용자 아이디로 사용자 정보 반환
	 *
	 * @param account 유저 아이디
	 * @return 사용자 객체
	 */
	public User getUserByAccount(String account) {
		SqlSession session = getSqlSession();
		try {
			return session.selectOne(USER_MAPPER + "getUserByAccount", account);
		} finally {
			session.close();
		}
	}


	public List<User> getUserList(int pageNumberOFFSET) {
		SqlSession session = getSqlSession();
		try {
			return session.selectList(USER_MAPPER + "getUserList", pageNumberOFFSET);
		} finally {
			session.close();
		}
	}

	public int getNumberOfUsers() {
		SqlSession session = getSqlSession();
		try {
			return session.selectOne(USER_MAPPER + "getNumberOfUsers");
		} finally {
			session.close();
		}
	}

	public int deleteUserById(int id) {
		SqlSession session = getSqlSession();
		try {
			return session.update(USER_MAPPER + "deleteUserById", id);
		} finally {
			session.close();
		}
	}

	public int updateDateDeleted(int id) {
		SqlSession session = getSqlSession();
		try {
			return session.update(USER_MAPPER + "updateDateDeleted", id);
		} finally {
			session.close();
		}
	}

	public int recoverUserById(int id) {
		SqlSession session = getSqlSession();
		try {
			return session.update(USER_MAPPER + "recoverUserById", id);
		} finally {
			session.close();
		}
	}

	public int recoverDateDeleted(int id) {
		SqlSession session = getSqlSession();
		try {
			return session.update(USER_MAPPER + "recoverDateDeleted", id);
		} finally {
			session.close();
		}
	}

}
