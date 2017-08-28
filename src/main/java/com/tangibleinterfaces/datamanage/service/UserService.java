package com.tangibleinterfaces.datamanage.service;

import java.util.List;

import com.tangibleinterfaces.datamanage.domain.User;

public interface UserService {

	List<User> findAll();

	void delete(String id);

	boolean saveUser(User user);

	User findByUsername(String id);

	String updateUser(User user, String username);

	List<User> findModerator();
}
