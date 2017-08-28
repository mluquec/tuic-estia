package com.tangibleinterfaces.datamanage.repository;

import java.util.List;

import com.tangibleinterfaces.datamanage.domain.Characteristic;

public interface CharacteristicRepository {
	
	void save(Characteristic characteristic);
	List<Characteristic> findAll();
	void delete(String characteristic);
	void update(Characteristic characteristic, String name);
	Characteristic findByName(String name);
	
	/// for general information
	
	List<Characteristic> findAllGeneral();
	Characteristic findByNameGeneral(String name);
}
