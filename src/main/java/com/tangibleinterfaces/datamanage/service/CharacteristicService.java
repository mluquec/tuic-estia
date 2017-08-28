package com.tangibleinterfaces.datamanage.service;

import java.util.List;

import com.tangibleinterfaces.datamanage.domain.Characteristic;



public interface CharacteristicService {

	List<Characteristic> findAll();

	void delete(String id);

	String saveCharacteristic(Characteristic characteristic);

	Characteristic findByName(String id);

	String updateCharacteristic(Characteristic characteristic, String username);
	
	
	// for general
	
	List<Characteristic> findAllGeneral();
	Characteristic findByNameGeneral(String name);
}
