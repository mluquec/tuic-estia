package com.tangibleinterfaces.datamanage.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lowagie.text.pdf.codec.postscript.ParseException;
import com.tangibleinterfaces.datamanage.domain.InterfacePlace;
import com.tangibleinterfaces.datamanage.service.OptionsService;
import com.tangibleinterfaces.datamanage.service.TangibleService;

@Controller
@RequestMapping(value="/options")

public class OptionsController {

	@Autowired
	OptionsService optionsService;
	
	@Autowired
	TangibleService tangibleService;
	//list all request options published 
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView interfaceList() {

		ModelAndView model= new ModelAndView("/options/list");
		
		
		model.addObject("list",optionsService.findAll());
			
		return model;
			
	}
	
	//acept 	options tangible 
			@RequestMapping(value = "/accept{id}", method = RequestMethod.GET)
			public ModelAndView acceptOptionsTangible(@RequestParam("id") String id ) {
				
				ModelAndView model= new ModelAndView("redirect:/options/list");
				  optionsService.accept(optionsService.findOption(id));
				  

		    
				
				
				
				return model;

			}
			
			
	
}
