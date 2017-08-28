package com.tangibleinterfaces.datamanage.repository.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.tangibleinterfaces.datamanage.domain.Category;
import com.tangibleinterfaces.datamanage.domain.Form;
import com.tangibleinterfaces.datamanage.repository.CategoryRepository;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
	
	@Autowired(required = false)
	MongoTemplate mongoTemplate;
	
	@Override
	public void save(Category category) 
	{
		mongoTemplate.save(category);
	}

	@Override
	public List<Category> findAll() {
		Query query= new Query();
		query.with(new Sort(Sort.Direction.ASC,"name"));
		return mongoTemplate.find(query,Category.class);
	}



	@Override
	public Category findByName(String name) {
		Query query = new Query(Criteria.where("name").is(name));
		return mongoTemplate.findOne(query,Category.class);
	}

	

	@Override
	public void delete(String name) {
		Query query = new Query(Criteria.where("name").is(name));
		mongoTemplate.findAndRemove(query, Category.class);
		Query query2 = new Query();
		Update update = 
				   new Update().pull("categories",name);
		mongoTemplate.updateMulti(query2, update, Form.class);
	}

	

	
}
