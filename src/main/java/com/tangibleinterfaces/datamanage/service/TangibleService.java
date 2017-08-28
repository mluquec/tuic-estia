package com.tangibleinterfaces.datamanage.service;

import java.util.List;


import com.tangibleinterfaces.datamanage.domain.TangibleInterface;

public interface TangibleService {

	List<TangibleInterface> findAll();

	String[] getBasicNames();

	TangibleInterface getModel();

	String save(TangibleInterface tangible);

	TangibleInterface findByPk(String id);

	void delete(String id);

	String updateTangible(TangibleInterface tangible, String id);

	TangibleInterface createModelMixed(TangibleInterface actual);

	TangibleInterface getNewOptions(TangibleInterface tangible);

	
	
}
