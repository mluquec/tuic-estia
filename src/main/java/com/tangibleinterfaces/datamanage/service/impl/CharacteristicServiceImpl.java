package com.tangibleinterfaces.datamanage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tangibleinterfaces.datamanage.domain.Characteristic;
import com.tangibleinterfaces.datamanage.repository.CharacteristicRepository;
import com.tangibleinterfaces.datamanage.service.CharacteristicService;

@Service
public class CharacteristicServiceImpl implements CharacteristicService {

	@Autowired
	CharacteristicRepository characteristicRepository;
	
	@Override
	public List<Characteristic> findAll() {
	
		return characteristicRepository.findAll();
	}

	@Override
	public void delete(String id) {
		characteristicRepository.delete(id);
		
	}

	@Override
	public String saveCharacteristic(Characteristic characteristic) {
		if(characteristicRepository.findByName(characteristic.getName()) == null)
		{
			characteristicRepository.save(characteristic);
			return "Success";
		}
		else{
			return "The characteristic is already registered";
		}
		
	}

	@Override
	public Characteristic findByName(String id) {
		return characteristicRepository.findByName(id);
	}

	@Override
	public String updateCharacteristic(Characteristic characteristic, String name) {
		characteristicRepository.delete(name);
		characteristicRepository.save(characteristic);
		
		if(characteristicRepository.findByName(characteristic.getName())!= null)
		{
			return "Success";
		}
		else
		{
			return "We can't update the characteristic";
		}
	}

	@Override
	public List<Characteristic> findAllGeneral() {
		return characteristicRepository.findAllGeneral();
	}

	@Override
	public Characteristic findByNameGeneral(String name) {
		return characteristicRepository.findByNameGeneral(name);
	}



}
