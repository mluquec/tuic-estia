package com.tangibleinterfaces.datamanage.repository;

import java.util.List;

import com.tangibleinterfaces.datamanage.domain.User;

public interface UserRepository {
	User findByUsername(String username);
	User save(User user);
	User existUser(String username, String password);
	List<User> findAll();
	void delete(String nameBefore);
	List<User> findModerator();

}