package com.tangibleinterfaces.datamanage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tangibleinterfaces.datamanage.domain.Characteristic;
import com.tangibleinterfaces.datamanage.domain.Form;
import com.tangibleinterfaces.datamanage.domain.InterfacePlace;
import com.tangibleinterfaces.datamanage.domain.Modification;
import com.tangibleinterfaces.datamanage.domain.Options;
import com.tangibleinterfaces.datamanage.domain.TangibleCategory;
import com.tangibleinterfaces.datamanage.domain.TangibleCharacteristic;
import com.tangibleinterfaces.datamanage.domain.TangibleInterface;
import com.tangibleinterfaces.datamanage.domain.TypeCategory;
import com.tangibleinterfaces.datamanage.domain.TypeInfo;
import com.tangibleinterfaces.datamanage.domain.TypeModification;
import com.tangibleinterfaces.datamanage.repository.CharacteristicRepository;
import com.tangibleinterfaces.datamanage.repository.FormRepository;
import com.tangibleinterfaces.datamanage.repository.OptionsRepository;
import com.tangibleinterfaces.datamanage.repository.TangibleRepository;
import com.tangibleinterfaces.datamanage.service.TangibleService;

@Service
public class TangibleServiceImpl implements TangibleService{
	
	@Autowired
	TangibleRepository tangibleRepository;
	@Autowired
	FormRepository formRepository;
	@Autowired
	CharacteristicRepository characteristicRepository;
	@Autowired
	OptionsRepository optionsRepository;
	
	@Override
	public List<TangibleInterface> findAll() {
		
		return tangibleRepository.findAll();
	}

	@Override
	public String[] getBasicNames() {
		Form form = formRepository.findByName("model");
		String[] generals = form.getGeneral();
		List<String> basic= new ArrayList<String>();
		for (String general : generals) {
			Characteristic tmp= characteristicRepository.findByNameGeneral(general);
			if(tmp.getTypeCategory() == TypeCategory.BASIC)
			{
				basic.add(tmp.getName());
			}
		}
		return basic.toArray(new String[basic.size()]);
	}

	@Override
	public TangibleInterface getModel() {
		return tangibleRepository.getModel();
	}

	@Override
	public String save(TangibleInterface tangible) {
		
		
		
		if(tangibleRepository.findByName(tangible.getPk()) == null)
		{
			tangibleRepository.save(tangible);
			return "Success";
		}
		else{
			return "The interface is already registered";
		}
		
		
	}

	@Override
	public TangibleInterface findByPk(String id) {
		// TODO Auto-generated method stub
		return tangibleRepository.findByName(id);
	}

	@Override
	public void delete(String id) {
		tangibleRepository.delete(id);
		
	}

	@Override
	public String updateTangible(TangibleInterface tangible, String id) {
		tangibleRepository.delete(id);
		tangibleRepository.save(tangible);
		
		if(tangibleRepository.findByName(tangible.getPk())!= null)
		{
			return "Success";
		}
		else
		{
			return "We can't update the interface";
		}
	}

	@Override
	public TangibleInterface createModelMixed(TangibleInterface actual) {
		
		
		TangibleInterface model= tangibleRepository.getModel();
		
		for ( TangibleCharacteristic basic : model.getBasic()) {
			
			TangibleCharacteristic actualCharacteristic = findCharacteristic(actual, basic.getName(), "BASIC", "");
			
			basic.setValue(actualCharacteristic.getValue());
			basic.setValueList(actualCharacteristic.getValueList());
		}
		
		for ( TangibleCharacteristic complementary : model.getComplementary()) {
			TangibleCharacteristic actualCharacteristic = findCharacteristic(actual, complementary.getName(), "COMPLEMENTARY", "");
			
			complementary.setValue(actualCharacteristic.getValue());
			complementary.setValueList(actualCharacteristic.getValueList());
		}
		
		for (TangibleCategory category : model.getCategories()) {
			for (TangibleCharacteristic characteristic : category.getCharacteristics()) {
				TangibleCharacteristic actualCharacteristic = findCharacteristic(actual, characteristic.getName(), "CATEGORY", category.getName());
				
				characteristic.setValue(actualCharacteristic.getValue());
				characteristic.setValueList(actualCharacteristic.getValueList());
			}
		}
		return model;
		
	}

public TangibleCharacteristic findCharacteristic(TangibleInterface actual, String name, String type, String nameCategory) {
		
		
		
		
		if(type.equals("BASIC"))
		{
			
			for ( TangibleCharacteristic basic : actual.getBasic()) {
				
				
				if(basic.getName().equals(name))
				{
					
					return basic;
				}
				
			}
		}
		else{
			if(type.equals("COMPLEMENTARY"))
			{
					for ( TangibleCharacteristic complementary : actual.getComplementary()) {
						if(complementary.getName().equals(name))
						{
							return complementary;
						}
					
					}
			}
			else{
				if(type.equals("CATEGORY"))
				{
						for ( TangibleCategory category : actual.getCategories()) {
							if(category.getName().equals(nameCategory))
							{
								for (TangibleCharacteristic  characteristic:  category.getCharacteristics()) {
									if(characteristic.getName().equals(name) )
									{
										return characteristic;
									}
									
								}
							}
						}
				}
				
			}
		}
		
		return new TangibleCharacteristic();
		
		
			
		
	}

@Override
public TangibleInterface getNewOptions(TangibleInterface tangible) {
	
for ( TangibleCharacteristic characteristic: tangible.getBasic()) {
		
		if(characteristic.getType().equals(TypeInfo.LIST.toString()))
		{
			String[]  newOptions =characteristic.getNewOptions().split(",");
			characteristic.setValueList((String[])ArrayUtils.addAll(characteristic.getValueList(), newOptions));
			if(newOptions.length >1)
			{
				Options optionsRequest = new Options();
				optionsRequest.setCategory("basic");
				Long dateid= new Date().getTime(); 
				optionsRequest.setId(Long.toString(dateid));
				optionsRequest.setCharacteristic(characteristic.getName());
				optionsRequest.setDateModification(new Date());
				optionsRequest.setNewOptions(characteristic.getNewOptions());
				
				optionsRepository.save(optionsRequest);
			}
			
		}
	}
	
	for ( TangibleCharacteristic characteristic: tangible.getComplementary()) {
		
		if(characteristic.getType().equals(TypeInfo.LIST.toString()))
		{
			String[]  newOptions =characteristic.getNewOptions().split(",");
			characteristic.setValueList((String[])ArrayUtils.addAll(characteristic.getValueList(), newOptions));
			
			if(newOptions.length >1)
			{
				Options optionsRequest = new Options();
				Long dateid= new Date().getTime(); 
				optionsRequest.setId(Long.toString(dateid));
				optionsRequest.setCategory("complementary");
				optionsRequest.setCharacteristic(characteristic.getName());
				optionsRequest.setDateModification(new Date());
				optionsRequest.setNewOptions(characteristic.getNewOptions());
				
				optionsRepository.save(optionsRequest);
			}
		}
	}
	
	for (TangibleCategory category: tangible.getCategories())
	{
		for ( TangibleCharacteristic characteristic: category.getCharacteristics()) {
			
			if(characteristic.getType().equals(TypeInfo.LIST.toString()))
			{
				String[]  newOptions =characteristic.getNewOptions().split(",");
				characteristic.setValueList((String[])ArrayUtils.addAll(characteristic.getValueList(), newOptions));
				if(newOptions.length >1)
				{
					Options optionsRequest = new Options();
					Long dateid= new Date().getTime(); 
					optionsRequest.setId(Long.toString(dateid));
					optionsRequest.setCategory(category.getName());
					optionsRequest.setCharacteristic(characteristic.getName());
					optionsRequest.setDateModification(new Date());
					optionsRequest.setNewOptions(characteristic.getNewOptions());
					
					optionsRepository.save(optionsRequest);
				}
			}
		}
	}
	
	return tangible;
}

}
