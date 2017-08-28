package com.tangibleinterfaces.datamanage.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.tangibleinterfaces.datamanage.domain.Category;
import com.tangibleinterfaces.datamanage.domain.Characteristic;
import com.tangibleinterfaces.datamanage.domain.TypeInfo;
import com.tangibleinterfaces.datamanage.repository.CharacteristicRepository;

@Repository
public class CharacteristicRepositoryImpl implements CharacteristicRepository {

	@Autowired(required = false)
	MongoTemplate mongoTemplate;
	
	@Override
	public void save(Characteristic characteristic) 
	{
		mongoTemplate.save(characteristic);
	}

	@Override
	public List<Characteristic> findAll() {
		Query query = new Query(Criteria.where("typeCategory").is("OTHER")).with(new Sort(Sort.Direction.ASC, "name"));
		
		return mongoTemplate.find(query, Characteristic.class);
	}

	
	@Override
	public void update(Characteristic characteristic, String name) {
		Query query = new Query(Criteria.where("name").is(name));
		
		mongoTemplate.updateFirst(query,Update.update("description", characteristic.getDescription()), Characteristic.class);
		mongoTemplate.updateFirst(query,Update.update("typeInfo", characteristic.getTypeInfo()), Characteristic.class);
		
		if(characteristic.getTypeInfo()== TypeInfo.LIST )
		{
			mongoTemplate.updateFirst(query,Update.update("options", characteristic.getOptions()), Characteristic.class);

		}
	
		
	}

		
	

	@Override
	public void delete(String characteristic) {
		Query query = new Query(Criteria.where("name").is(characteristic));
		mongoTemplate.findAndRemove(query, Characteristic.class);
		Query query2 = new Query();
		Update update = 
				   new Update().pull("characteristics",characteristic);
		mongoTemplate.updateMulti(query2, update, Category.class);
		
	}

	@Override
	public Characteristic findByName(String name) {
		Query query = new Query(Criteria.where("name").is(name));
		return mongoTemplate.findOne(query, Characteristic.class);
	}

	@Override
	public List<Characteristic> findAllGeneral() {
		Criteria criteria = new Criteria();
		criteria.orOperator(Criteria.where("typeCategory").is("BASIC"), Criteria.where("typeCategory").is("COMPLEMENTARY"));
		Query query3 = new Query(criteria).with(new Sort(Sort.Direction.ASC,"name"));

		return mongoTemplate.find(query3, Characteristic.class);
	}

	@Override
	public Characteristic findByNameGeneral(String name) {
		Query query = new Query(Criteria.where("name").is(name));
		query.with(new Sort(Sort.Direction.ASC,"name"));
		return mongoTemplate.findOne(query, Characteristic.class);
	}


}
