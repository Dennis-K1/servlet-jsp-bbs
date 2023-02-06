package com.bbs.service;

import com.bbs.domain.User;
import com.bbs.dao.UserDAO;
import com.bbs.util.Role;
import java.util.Objects;

/**
 * 사용자 관련 서비스
 */
public class UserService {

	/**
	 * 해당 아이디의 사용자 반환
	 * @param account 사용자 아이디
	 * @return 사용자 정보를 담은 User 객체
	 */
	public User getUser(String account) {
		UserDAO userDAO = new UserDAO();
		return userDAO.getUser(account);
	}

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

		Long roleId = userDAO.getUser(admin.getAccount()).getRoleId();

		String roleName = userDAO.getRoleName(roleId);

		if (roleName.equals(Role.ADMIN.toString())) {
			return true;
		}
		return false;
	}

	public boolean isExistingUser(User user) {
		UserDAO userDAO = new UserDAO();
		if (Objects.equals(null, userDAO.getUser(user.getAccount()))) {
			return false;
		}
		return true;
	}

	public boolean isCorrectPassword(User user) {
		UserDAO userDAO = new UserDAO();
		String userPassword = userDAO.getUserPassword(user.getAccount());
		if (userPassword.equals(user.getPassword())) {
			return true;
		}
		return false;
	}
}
