package com.tangibleinterfaces.datamanage.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.tangibleinterfaces.datamanage.domain.User;
import com.tangibleinterfaces.datamanage.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository{


	@Autowired(required = false)
	MongoTemplate mongoTemplate;

	@Override
	public User findByUsername(String username) {
		Query query = new Query(Criteria.where("username").is(username));
		return mongoTemplate.findOne(query, User.class);
	}

	
	@Override
	public User save(User user) 
	{
		mongoTemplate.save(user);
		return user;
		}


	@Override
	public User existUser(String username, String password) {
		Query query = new Query(new Criteria().andOperator(
				  Criteria.where("username").is(username),
				  Criteria.where("password").is(password)
				));
		return mongoTemplate.findOne(query, User.class);
		
	}


	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(User.class);
	}
	
	@Override
	public void delete(String name) {
		Query query = new Query(Criteria.where("username").is(name));
		mongoTemplate.findAndRemove(query, User.class);
	}


	@Override
	public List<User> findModerator() {
		Query query = new Query(new Criteria().orOperator(
				  Criteria.where("role").is("ADMIN"),
				  Criteria.where("role").is("MODERATOR")
				));
		return mongoTemplate.find(query, User.class);
	}
}
