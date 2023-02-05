package com.bbs.service;

import com.bbs.domain.User;
import com.bbs.dao.UserDAO;

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
}
