package com.tangibleinterfaces.datamanage.repository.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.tangibleinterfaces.datamanage.domain.Form;
import com.tangibleinterfaces.datamanage.domain.Modification;
import com.tangibleinterfaces.datamanage.domain.Options;
import com.tangibleinterfaces.datamanage.repository.OptionsRepository;

@Repository
public class OptionsRepositoryImpl implements OptionsRepository {

	
	@Autowired(required = false)
	MongoTemplate mongoTemplate;
	
	
	@Override
	public void save(Options option) {
		mongoTemplate.save(option);
		
	}


	@Override
	public List<Options> findAll() {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(Options.class);
	}


	@Override
	public Options findOption(String id) {
		
			Query query = new Query(Criteria.where("id").is(id));
		
		return mongoTemplate.findOne(query,Options.class);

	}


	@Override
	public void delete(String id) {
		Query query = new Query(Criteria.where("id").is(id));
		mongoTemplate.findAndRemove(query, Options.class);
		
	}

	
}
