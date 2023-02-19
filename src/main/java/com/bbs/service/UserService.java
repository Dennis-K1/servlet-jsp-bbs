package com.bbs.service;

import com.bbs.config.MybatisSqlSessionFactory;
import com.bbs.mapper.UserMapper;
import com.bbs.domain.User;
import com.bbs.domain.Role;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * 사용자 관련 서비스
 */
public class UserService {

	/**
	 * 마이바티스 및 매퍼 실행을 위한 SqlSessionFactory
	 */
	private SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();

	/**
	 * 유저 등록
	 *
	 * @param user 유저가 입력한 정보로 생성한 사용자 객체
	 * @return
	 */
	public int registerUser(User user) {
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			UserMapper userMapper = session.getMapper(UserMapper.class);
			return userMapper.registerUser(user);
		}
	}

	/**
	 * 어드민 권한 확인
	 *
	 * @param admin 사용자 정보 객체
	 * @return boolean
	 */
	public boolean isAdmin(User admin) {
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			UserMapper userMapper = session.getMapper(UserMapper.class);
			Long roleId = userMapper.getUserByAccount(admin.getAccount()).getRoleId();

			String roleName = userMapper.getRoleName(roleId);

			if (roleName.equals(Role.ADMIN.toString())) {
				return true;
			}
			return false;
		}
	}

	/**
	 * 로그인 유저가 입력한 아이디, 비밀번호 검증
	 *
	 * @param user 유저가 입력한 정보로 생성한 사용자 객체
	 * @return 통과시 사용자 객체, 미통과시 null
	 */
	public User login(User user) {
		try (SqlSession session = sqlSessionFactory.openSession(true)){
			UserMapper userMapper = session.getMapper(UserMapper.class);
			User userDetail = userMapper.getUserByAccount(user.getAccount());
			if (userDetail == null) {
				return null;
			}
			if (!user.getPassword().equals(userDetail.getPassword())) {
				return null;
			}
			return userDetail;
		}
	}

	/**
	 * 검색조건 기반 클라이언트 조회
	 *
	 * @param pageNumberOffset 조회 시작 인덱스
	 * @return
	 */
	public List<User> getUserList(int pageNumberOffset) {
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			UserMapper userMapper = session.getMapper(UserMapper.class);
			return userMapper.getUserList(pageNumberOffset);
		}
	}

	/**
	 * 총 클라이언트 수 조회
	 *
	 * @return
	 */
	public int getNumberOfUsers() {
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			UserMapper userMapper = session.getMapper(UserMapper.class);
			return userMapper.getNumberOfUsers();
		}
	}

	/**
	 * 대상 클라이언트 삭제 처리 (삭제 처리, 삭제일 갱신)
	 *
	 * @param id 클라이언트 번호
	 * @return
	 */
	public int deleteUserById(int id) {
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			UserMapper userMapper = session.getMapper(UserMapper.class);
			if (userMapper.deleteUserById(id) == 1) {
				return userMapper.updateDateDeleted(id);
			}
			return 0;
		}
	}

	/**
	 * 대상 클라이언트 복구 처리 (복구 처리, 삭제일 갱신)
	 *
	 * @param id 클라이언트 번호
	 * @return
	 */
	public int recoverUserById(int id) {
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			UserMapper userMapper = session.getMapper(UserMapper.class);
			if (userMapper.recoverUserById(id) == 1) {
				return userMapper.recoverDateDeleted(id);
			}
			return 0;
		}
	}
}
