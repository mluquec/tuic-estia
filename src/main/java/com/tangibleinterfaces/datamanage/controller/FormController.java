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
import com.tangibleinterfaces.datamanage.domain.Form;
import com.tangibleinterfaces.datamanage.service.CategoryService;
import com.tangibleinterfaces.datamanage.service.CharacteristicService;
import com.tangibleinterfaces.datamanage.service.FormService;

@Controller
@RequestMapping(value="/forms")
public class FormController {
	
	@Autowired
	FormService formService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	CharacteristicService characteristicService;
	
	//list all forms 
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView formsList() {

		ModelAndView model= new ModelAndView("/forms/list");
		model.addObject("list",formService.findAll());
		
		return model;
		
	}
	
	//add category from admin
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addFormForm() {
		ModelAndView model= new ModelAndView("/forms/add");
		model.addObject("form", new Form());
		Map<String, String> categories = new HashMap<String, String>();
		Map<String, String> generals = new HashMap<String, String>();
	    List<Category> categoriesList= categoryService.findAll();
	    List<Characteristic> characteristicsList= characteristicService.findAllGeneral();
	    for (Category category : categoriesList) {
			categories.put(category.getName(),category.getName());
		}    
	    for (Characteristic characteristic : characteristicsList) {
			generals.put(characteristic.getName(),characteristic.getName());
		}        
	    model.addObject("categoriesMap", categories);
	    model.addObject("generalsMap", generals);
	    return model;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addForm(@Valid Form form, RedirectAttributes redir) {
		
		
		return new ModelAndView("redirect:/forms/list","estade",formService.save(form));
	}
	
	//update category
	@RequestMapping(value = "/update{id}", method = RequestMethod.GET)
	public ModelAndView  updateFormForm(@RequestParam String id) {
		ModelAndView model= new ModelAndView("/forms/add");
		model.addObject("form", formService.findByName(id));
		Map<String, String> categories = new HashMap<String, String>();
		Map<String, String> generals = new HashMap<String, String>();
	    List<Category> categoriesList= categoryService.findAll();
	    List<Characteristic> characteristicsList= characteristicService.findAllGeneral();
	    for (Category category : categoriesList) {
			categories.put(category.getName(),category.getName());
		}    
	    for (Characteristic characteristic : characteristicsList) {
			generals.put(characteristic.getName(),characteristic.getName());
		}        
	    model.addObject("categoriesMap", categories);
	    model.addObject("generalsMap", generals);
	    return model;
	}
	
	@RequestMapping(value = "/update{id}", method = RequestMethod.POST)
	public ModelAndView updateForm(@Valid Form form, @RequestParam String id) {
		
		String result= formService.updateForm(form,id);
		
		ModelAndView model= new ModelAndView("redirect:/forms/list" ,"estade",result);
		
		return model;
	}
	
	//delete 	form
	@RequestMapping(value = "/delete{id}", method = RequestMethod.GET)
	public ModelAndView deleteForm(@RequestParam String id) {
		ModelAndView model= new ModelAndView("redirect:/forms/list");
		formService.delete(id);
		return model;

	}
	
	//active 	form
	@RequestMapping(value = "/active{id}", method = RequestMethod.GET)
	public ModelAndView activeForm(@RequestParam String id) {
		
		String result=formService.active(id);
		
		formService.delete("model");
		
		formService.createModel();
		
		ModelAndView model= new ModelAndView("redirect:/forms/list","estade",result);
		return model;

	}
	
	//view forms 
	@RequestMapping(value = "/view{id}", method = RequestMethod.GET)
	public ModelAndView formView(@RequestParam String id) {

		ModelAndView model= new ModelAndView("/forms/view");
		model.addObject("form", formService.findByName(id));
		model.addObject("basic",formService.getBasic(id));
		model.addObject("complementary",formService.getComplementary(id));
		model.addObject("categoryNames",formService.findByName(id).getCategories());
		model.addObject("characteristics",formService.getCategories(id));
		return model;
			
		}
}
