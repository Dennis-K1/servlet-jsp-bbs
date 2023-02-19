package com.bbs.mapper;

import com.bbs.domain.User;
import java.util.List;

public interface UserMapper {

	public String getRoleName(Long roleId);

	public int registerUser(User user);

	public User getUserByAccount(String account);

	public List<User> getUserList(int pageNumberOffset);

	public int getNumberOfUsers();

	public int deleteUserById(int id);

	public int updateDateDeleted(int id);

	public int recoverUserById(int id);

	public int recoverDateDeleted(int id);
}
