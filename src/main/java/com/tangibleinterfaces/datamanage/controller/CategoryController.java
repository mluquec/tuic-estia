package com.tangibleinterfaces.datamanage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tangibleinterfaces.datamanage.domain.Category;
import com.tangibleinterfaces.datamanage.domain.Characteristic;
import com.tangibleinterfaces.datamanage.domain.TypeCategory;
import com.tangibleinterfaces.datamanage.domain.TypeInfo;
import com.tangibleinterfaces.datamanage.service.CategoryService;
import com.tangibleinterfaces.datamanage.service.CharacteristicService;

@Controller
@RequestMapping(value="/categories")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	CharacteristicService characteristicService;
	
	
	//list all categories not basic or complementary
			@RequestMapping(value = "/list", method = RequestMethod.GET)
			public ModelAndView categoriesList() {

				ModelAndView model= new ModelAndView("/categories/list");
				model.addObject("list",categoryService.findAll());
				
				return model;
				
			}
			
			//add category from admin
			@RequestMapping(value = "/add", method = RequestMethod.GET)
			public ModelAndView addCategoryForm() {
				ModelAndView model= new ModelAndView("/categories/add");
				model.addObject("category", new Category());
				Map<String, String> characteristics = new HashMap<String, String>();
			    List<Characteristic> characteristicsList= characteristicService.findAll();
			    for (Characteristic characteristic : characteristicsList) {
					characteristics.put(characteristic.getName(),characteristic.getName());
				}    
			         
			        model.addObject("characteristicsMap", characteristics);
				return model;
			}
			
			@RequestMapping(value = "/add", method = RequestMethod.POST)
			public ModelAndView addCategory(@Valid Category category, RedirectAttributes redir) {
				
				
				return new ModelAndView("redirect:/categories/list","estade",categoryService.save(category));
			}
			
			//update category
			@RequestMapping(value = "/update{id}", method = RequestMethod.GET)
			public ModelAndView  updateCategoryForm(@RequestParam String id) {
				ModelAndView model= new ModelAndView("/categories/add");
				model.addObject("category", categoryService.findByName(id));
				Map<String, String> characteristics = new HashMap<String, String>();
			    List<Characteristic> characteristicsList= characteristicService.findAll();
			    for (Characteristic characteristic : characteristicsList) {
					characteristics.put(characteristic.getName(),characteristic.getName());
				}    
			         
			        model.addObject("characteristicsMap", characteristics);
				return model;
			}
			
			@RequestMapping(value = "/update{id}", method = RequestMethod.POST)
			public ModelAndView updateCategory(@Valid Category category, @RequestParam String id) {
				
				String result= categoryService.updateCategory(category,id);
				
				ModelAndView model= new ModelAndView("redirect:/categories/list" ,"estade",result);
				
				return model;
			}
			
			//delete category
			@RequestMapping(value = "/delete{id}", method = RequestMethod.GET)
			public ModelAndView deleteCategory(@RequestParam String id) {
				ModelAndView model= new ModelAndView("redirect:/categories/list");
				categoryService.delete(id);
				return model;

			}
}
