package com.bbs.service;

import com.bbs.domain.User;
import com.bbs.dao.UserDAO;
import com.bbs.domain.Role;
import java.util.List;

/**
 * 사용자 관련 서비스
 */
public class UserService {


	/**
	 * 유저 등록
	 *
	 * @param user 유저가 입력한 정보로 생성한 사용자 객체
	 * @return
	 */
	public int registerUser(User user) {
		UserDAO userDAO = new UserDAO();
		return userDAO.registerUser(user);
	}

	/**
	 * 어드민 권한 확인
	 *
	 * @param admin 사용자 정보 객체
	 * @return boolean
	 */
	public boolean isAdmin(User admin) {
		UserDAO userDAO = new UserDAO();

		Long roleId = userDAO.getUserByAccount(admin.getAccount()).getRoleId();

		String roleName = userDAO.getRoleName(roleId);

		if (roleName.equals(Role.ADMIN.toString())) {
			return true;
		}
		return false;
	}

	/**
	 * 로그인 유저가 입력한 아이디, 비밀번호 검증
	 *
	 * @param user 유저가 입력한 정보로 생성한 사용자 객체
	 * @return 통과시 사용자 객체, 미통과시 null
	 */
	public User login(User user) {
		UserDAO userDAO = new UserDAO();
		User userDetail = userDAO.getUserByAccount(user.getAccount());
		if (userDetail == null) {
			return null;
		}
		if (!user.getPassword().equals(userDetail.getPassword())) {
			return null;
		}
		return userDetail;
	}

	/**
	 * 검색조건 기반 클라이언트 조회
	 *
	 * @param pageNumberOffset 조회 시작 인덱스
	 * @return
	 */
	public List<User> getUserList(int pageNumberOffset) {
		UserDAO userDAO = new UserDAO();
		return userDAO.getUserList(pageNumberOffset);
	}

	/**
	 * 총 클라이언트 수 조회
	 *
	 * @return
	 */
	public int getNumberOfUsers() {
		UserDAO userDAO = new UserDAO();
		return userDAO.getNumberOfUsers();
	}

	/**
	 * 대상 클라이언트 삭제 처리 (삭제 처리, 삭제일 갱신)
	 *
	 * @param id 클라이언트 번호
	 * @return
	 */
	public int deleteUserById(int id) {
		UserDAO userDAO = new UserDAO();
		if (userDAO.deleteUserById(id) == 1){
			return userDAO.updateDateDeleted(id);
		}
		return 0;
	}

	/**
	 * 대상 클라이언트 복구 처리 (복구 처리, 삭제일 갱신)
	 *
	 * @param id 클라이언트 번호
	 * @return
	 */
	public int recoverUserById(int id) {
		UserDAO userDAO = new UserDAO();
		if (userDAO.recoverUserById(id) == 1){
			return userDAO.recoverDateDeleted(id);
		}
		return 0;
	}
}
