package com.tangibleinterfaces.datamanage.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tangibleinterfaces.datamanage.domain.Characteristic;
import com.tangibleinterfaces.datamanage.domain.Options;
import com.tangibleinterfaces.datamanage.domain.TangibleCategory;
import com.tangibleinterfaces.datamanage.domain.TangibleCharacteristic;
import com.tangibleinterfaces.datamanage.domain.TangibleInterface;
import com.tangibleinterfaces.datamanage.repository.CharacteristicRepository;
import com.tangibleinterfaces.datamanage.repository.OptionsRepository;
import com.tangibleinterfaces.datamanage.repository.TangibleRepository;
import com.tangibleinterfaces.datamanage.service.FormService;
import com.tangibleinterfaces.datamanage.service.OptionsService;
@Service
public class OptionsServiceImpl  implements OptionsService{

	
	@Autowired
	OptionsRepository optionsRepository;
	
	@Autowired
	TangibleRepository tangibleRepository;
	
	@Autowired
	FormService formService;
	
	@Autowired
	CharacteristicRepository characteristicRepository;
	
	@Override
	public List<Options> findAll() {
		
		return optionsRepository.findAll();
	}

	@Override
	public Options findOption(String id) {
		// TODO Auto-generated method stub
		return optionsRepository.findOption(id);
	}

	@Override
	public void accept(Options option) {
		
		Characteristic characteristic = characteristicRepository.findByName(option.getCharacteristic());
		String newOptions= characteristic.getOptions()+","+option.getNewOptions();
		String[] options = newOptions.split(",");
		int i=0;
		for (String optionNew : options) {
			options[i]=optionNew.trim();
			i++;
		}
		Arrays.sort(options); 
		
		
		characteristic.setOptions(String.join(",",options));
		characteristicRepository.delete(characteristic.getName());
		characteristicRepository.save(characteristic);
		option.setDateAprovement(new Date());
		
		optionsRepository.delete(option.getId());
		optionsRepository.save(option);
		tangibleRepository.delete("model");
		formService.createModel();
	}

}
