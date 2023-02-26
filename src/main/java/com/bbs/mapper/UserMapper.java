package com.bbs.mapper;

import com.bbs.domain.PageParameters;
import com.bbs.domain.User;
import java.util.List;

public interface UserMapper {

	String getRoleName(Long roleId);

	int registerUser(User user);

	User getUserByAccount(String account);

	List<User> getUserList(PageParameters pageParameters);

	int getNumberOfUsersBySearch(PageParameters pageParameters);

	int deleteUserById(int id);

	int updateDateDeleted(int id);

	int recoverUserById(int id);

	int recoverDateDeleted(int id);

	int increaseVisitCount(User user);

	int updateLastLogin(User user);

	int increaseArticleCount(User user);

	int decreaseArticleCountByAccount(String account);
}
