package com.tangibleinterfaces.datamanage.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.tangibleinterfaces.datamanage.domain.Characteristic;
import com.tangibleinterfaces.datamanage.domain.Form;
import com.tangibleinterfaces.datamanage.repository.FormRepository;

@Repository
public class FormRepositoryImpl implements FormRepository {
	

	
	@Autowired(required = false)
	MongoTemplate mongoTemplate;

	@Override
	public void save(Form form) {
		mongoTemplate.save(form);
		
	}

	@Override
	public List<Form> findAll() {
	
		return mongoTemplate.findAll(Form.class);
	}

	@Override
	public Form findByName(String name) {
		Query query = new Query(Criteria.where("name").is(name));
		return mongoTemplate.findOne(query,Form.class);
	}

	@Override
	public void delete(String name) {
		Query query = new Query(Criteria.where("name").is(name));
		mongoTemplate.findAndRemove(query, Form.class);
		
	}

	@Override
	public Form findActive() {
		Query q = new Query(Criteria.where("isActive").is(true));		
		return mongoTemplate.findOne(q, Form.class);
	}

	@Override
	public void setActive(String id) {
		Query query3 = new Query(Criteria.where("isActive").is(true));
		mongoTemplate.updateFirst(query3,Update.update("isActive", false ),Form.class);
		Query query2 = new Query(Criteria.where("name").is(id));
		mongoTemplate.updateFirst(query2,Update.update("isActive", true ),Form.class);
		
	}

	
}
