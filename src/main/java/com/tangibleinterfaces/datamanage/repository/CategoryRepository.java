package com.tangibleinterfaces.datamanage.repository;

import java.util.List;

import com.tangibleinterfaces.datamanage.domain.Category;

public interface CategoryRepository {
	void save(Category category);
	List<Category> findAll();
	Category findByName(String name);
	void delete(String name);
	
}
