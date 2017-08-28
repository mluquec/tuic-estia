package com.tangibleinterfaces.datamanage.service;

import java.util.List;

import com.tangibleinterfaces.datamanage.domain.Category;



public interface CategoryService {

	List<Category> findAll();

	void delete(String id);

	String save(Category category);

	Category findByName(String id);

	String updateCategory(Category category, String name);
}
