package com.tangibleinterfaces.datamanage.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.tangibleinterfaces.datamanage.domain.Characteristic;
import com.tangibleinterfaces.datamanage.domain.Form;
import com.tangibleinterfaces.datamanage.domain.TangibleCategory;
import com.tangibleinterfaces.datamanage.domain.TangibleCharacteristic;
import com.tangibleinterfaces.datamanage.domain.TangibleInterface;
import com.tangibleinterfaces.datamanage.repository.TangibleRepository;
@Repository
public class TangibleRepositoryImpl implements TangibleRepository{
	
	@Autowired(required = false)
	MongoTemplate mongoTemplate;

	@Override
	public void save(TangibleInterface tangible) {
		mongoTemplate.save(tangible);
		
	}

	@Override
	public List<TangibleInterface> findAll() {
		Query query = new Query(Criteria.where("pk").ne("model"));
		return mongoTemplate.find(query,TangibleInterface.class);
	}

	@Override
	public TangibleInterface getModel() {
		Query query = new Query(Criteria.where("pk").is("model"));
		return mongoTemplate.findOne(query,TangibleInterface.class);
	}

	@Override
	public TangibleInterface findByName(String pk) {
		Query query = new Query(Criteria.where("pk").is(pk));
		return mongoTemplate.findOne(query,TangibleInterface.class);
	}

	@Override
	public void delete(String id) {
		Query query = new Query(Criteria.where("pk").is(id));
		mongoTemplate.findAndRemove(query, TangibleInterface.class);
	}

	@Override
	public TangibleInterface mixedParcial(TangibleInterface tangible, TangibleInterface publish, Form form) {
		
		publish.setPk(tangible.getPk());
		for (String general : form.getGeneral()) {
			System.out.println(general);
			String[]  generalC= general.split("--");
			String typeC= generalC[1].trim();
			String name= generalC[0].trim();
			String typeI= generalC[2].trim();
			String value= generalC[3].trim();
			
			if(typeC.equals("basic"))
			{
				
				for (TangibleCharacteristic characteristic : publish.getBasic()) {
					if(characteristic.getName().equals(name))
					{
						if(typeI.equals("list"))
						{
							characteristic.setValueList(value.split(","));
						}
						else
						{
							characteristic.setValue(value);
						}
						break;
					}
				}
			}
			else
			{
				if(typeC.equals("complementary"))
				{
					for (TangibleCharacteristic characteristic : publish.getComplementary()) {
						if(characteristic.getName().equals(name))
						{
							if(typeI.equals("list"))
							{
								characteristic.setValueList(value.split(","));
							}
							else
							{
								characteristic.setValue(value);
							}
							break;
						}
					}
				}
				
			}
		}
		
		
		for (String categoryT : form.getCategories())
		{
			System.out.println(categoryT);
			String[]  categoryC= categoryT.split("--");
			String typeC= categoryC[1].trim();
			String name= categoryC[0].trim();
			String typeI= categoryC[2].trim();
			String value= categoryC[3].trim();
			
			for(TangibleCategory category : publish.getCategories())
			{
				for(TangibleCharacteristic characteristic :category.getCharacteristics())
				{
					if(characteristic.getName().equals(name))
					{
						if(typeI.equals("list"))
						{
							characteristic.setValueList(value.split(","));
						}
						else
						{
							characteristic.setValue(value);
						}
						break;
					}
				}
			}
		}
		
		return publish;
	}
}
