package com.tangibleinterfaces.datamanage.repository;

import java.util.List;

import com.tangibleinterfaces.datamanage.domain.Form;
import com.tangibleinterfaces.datamanage.domain.TangibleInterface;

public interface TangibleRepository {
	void save(TangibleInterface tangible);

	List<TangibleInterface> findAll();

	TangibleInterface getModel();

	TangibleInterface findByName(String pk);

	void delete(String id);

	TangibleInterface mixedParcial(TangibleInterface tangible, TangibleInterface model, Form form);
}
