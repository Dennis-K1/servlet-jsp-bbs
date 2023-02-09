package com.bbs.service;

import com.bbs.domain.User;
import com.bbs.dao.UserDAO;
import com.bbs.util.Role;
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
	 * 로그인
	 * 유저가 입력한 아이디, 비밀번호 검증
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

	public List<User> getUserList(int pageNumber) {
		UserDAO userDAO = new UserDAO();
		return userDAO.getUserList(pageNumber);
	}
}
