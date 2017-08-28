package com.tangibleinterfaces.datamanage.repository;

import java.util.Date;
import java.util.List;

import com.tangibleinterfaces.datamanage.domain.Options;

public interface OptionsRepository {
	
	void save(Options option);

	List<Options> findAll();

	Options findOption(String id);

	void delete(String id);
}
