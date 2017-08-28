package com.tangibleinterfaces.datamanage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tangibleinterfaces.datamanage.domain.Category;
import com.tangibleinterfaces.datamanage.repository.CategoryRepository;
import com.tangibleinterfaces.datamanage.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public List<Category> findAll() {
		return  categoryRepository.findAll();
	}

	@Override
	public void delete(String id) {
		categoryRepository.delete(id);
		
	}

	@Override
	public String save(Category category) {
		if(categoryRepository.findByName(category.getName()) == null)
		{
			categoryRepository.save(category);
			return "Success";
		}
		else{
			return "The category is already registered";
		}
	}

	@Override
	public Category findByName(String id) {
		return categoryRepository.findByName(id);
	}

	@Override
	public String updateCategory(Category category, String name) {
		categoryRepository.delete(name);
		categoryRepository.save(category);
		
		if(categoryRepository.findByName(category.getName())!= null)
		{
			return "Success";
		}
		else
		{
			return "We can't update the category";
		}
	}

}
