package com.tangibleinterfaces.datamanage.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.tangibleinterfaces.datamanage.domain.InterfacePlace;
import com.tangibleinterfaces.datamanage.domain.Modification;

import com.tangibleinterfaces.datamanage.repository.ModificationRepository;

@Repository
public class ModificationRepositoryImpl implements ModificationRepository {
	
	
	@Autowired(required = false)
	MongoTemplate mongoTemplate;
	
	@Override
	public void save(Modification modification) {
		mongoTemplate.save(modification);
		
	}

	@Override
	public List<Modification> findMyInterfaces(String user, String place) {
		Query query = new Query(new Criteria().andOperator(
				  		  Criteria.where("user").is(user),
						  Criteria.where("interfacePlace").is(place))
				);
		return mongoTemplate.find(query,Modification.class);
	}

	@Override
	public Modification findByPkMyInterface(String user, String id, String place) {
		
		Query query = new Query(new Criteria().andOperator(
				  Criteria.where("user").is(user),
				  new Criteria().andOperator(
						  Criteria.where("interfacePlace").is(place),
						  Criteria.where("tangible._id").is(id)))
				);
		
		return mongoTemplate.findOne(query,Modification.class);
	}

	@Override
	public void deleteMyInterface(String user, String place, String pk) {
		Query query = new Query(new Criteria().andOperator(
				  Criteria.where("user").is(user),
				  new Criteria().andOperator(
						  Criteria.where("interfacePlace").is(place),
						  Criteria.where("tangible._id").is(pk)))
				);
		mongoTemplate.findAndRemove(query,Modification.class);
		
	}

	@Override
	public List<Modification> findMyRequest(String user, String place) {
		Query query = new Query(new Criteria().andOperator(
		  		  Criteria.where("user").is(user),
				  Criteria.where("interfacePlace").is(place))
		);
		return mongoTemplate.find(query,Modification.class);
	}

	@Override
	public List<Modification> findAllRequestAssign() {
		Query query = new Query(
				  Criteria.where("interfacePlace").is(InterfacePlace.ASIGNED.toString())
		);
		return mongoTemplate.find(query,Modification.class);
	}

	@Override
	public Modification findModification(String user, String place, String pk) {
		Query query = new Query(new Criteria().andOperator(
				  Criteria.where("user").is(user),
				  new Criteria().andOperator(
						  Criteria.where("interfacePlace").is(place),
						  Criteria.where("tangible.pk").is(pk)))
				);
		return mongoTemplate.findOne(query,Modification.class);
	}

	@Override
	public Modification findByPkMyInterfaceVersion(String user, String id, String place, Integer version) {
		
		System.out.print(user+id+place+version);
		Query query = new Query(new Criteria().andOperator(
				  Criteria.where("user").is(user), new Criteria().andOperator(Criteria.where("version").is(version),
				  new Criteria().andOperator(
						  Criteria.where("interfacePlace").is(place),
						  Criteria.where("tangible._id").is(id)))
				));
		return mongoTemplate.findOne(query,Modification.class);
	}

	@Override
	public void deleteModification(String user, String pk, String place, Integer version) {
	
		Query query = new Query(new Criteria().andOperator(
				  Criteria.where("user").is(user), new Criteria().andOperator(Criteria.where("version").is(version),
				  new Criteria().andOperator(
						  Criteria.where("interfacePlace").is(place),
						  Criteria.where("tangible._id").is(pk)))
				));
		mongoTemplate.findAndRemove(query,Modification.class);
	}

	@Override
	public List<Modification> findAll() {
		return mongoTemplate.findAll(Modification.class);
	}

	@Override
	public Modification getLastVersion(String id, String place) {
		System.out.println(id+place);
		Query query = new Query();
		query.addCriteria(new Criteria().andOperator(Criteria.where("interfacePlace").is(place),
													Criteria.where("tangible._id").in(id)));
		query.with(new Sort(Sort.Direction.DESC,"version"));
		
	
		return mongoTemplate.find(query, Modification.class).get(0); 
	}

	@Override
	public List<Modification> findMyInterfacesDashboard(String user, String place) {
		Query query = new Query(new Criteria().andOperator(
		  		  Criteria.where("userModerator").in(user),
				  Criteria.where("interfacePlace").is(place))
		);
		return mongoTemplate.find(query,Modification.class);
	}

}
