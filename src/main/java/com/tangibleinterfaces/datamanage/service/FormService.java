package com.tangibleinterfaces.datamanage.service;

import java.util.List;
import java.util.Map;

import com.tangibleinterfaces.datamanage.domain.Characteristic;
import com.tangibleinterfaces.datamanage.domain.Form;



public interface FormService {

	List<Form> findAll();

	void delete(String id);

	String save(Form form);

	Form findByName(String id);

	String updateForm(Form form, String name);
	
	String active(String id);
	
	List<Characteristic> getBasic(String id);

	List<Characteristic> getComplementary(String id);

	Map<String, List<Characteristic> > getCategories(String id);

	void createModel();

	String getPk();
}
