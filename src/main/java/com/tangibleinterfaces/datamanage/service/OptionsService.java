package com.tangibleinterfaces.datamanage.service;

import java.util.Date;
import java.util.List;

import com.tangibleinterfaces.datamanage.domain.Options;

public interface OptionsService {

	List<Options> findAll();

	Options findOption(String id);
	

	void accept(Options option);

	
}
