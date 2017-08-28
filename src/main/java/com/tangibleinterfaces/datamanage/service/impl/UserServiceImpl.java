package com.tangibleinterfaces.datamanage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tangibleinterfaces.datamanage.domain.User;
import com.tangibleinterfaces.datamanage.repository.UserRepository;
import com.tangibleinterfaces.datamanage.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void delete(String id) {
		userRepository.delete(id);
		
	}

	@Override
	public boolean saveUser(User user) {
		
		if(userRepository.findByUsername(user.getUsername()) !=null)
		{
			return false;
		}
		else
		{
			userRepository.save(user);
			return true;
		}
	}

	@Override
	public User findByUsername(String id) {
		return userRepository.findByUsername(id);
	}

	@Override
	public String updateUser(User user, String username) {
		userRepository.delete(username);
		userRepository.save(user);
		
		if(userRepository.findByUsername(user.getUsername())!= null)
		{
			return "Success";
		}
		else
		{
			return "We can't update the user";
		}
		
	}

	@Override
	public List<User> findModerator() {
		// TODO Auto-generated method stub
		return userRepository.findModerator();
	}

}
