package com.tangibleinterfaces.datamanage.repository;

import java.util.List;

import com.tangibleinterfaces.datamanage.domain.Characteristic;
import com.tangibleinterfaces.datamanage.domain.Form;

public interface FormRepository {
	void save(Form form);
	List<Form> findAll();
	Form findByName(String name);
	void delete(String name);
	Form findActive();
	void setActive(String id);
	
}
