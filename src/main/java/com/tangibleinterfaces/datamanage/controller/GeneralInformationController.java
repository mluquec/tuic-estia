package com.tangibleinterfaces.datamanage.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tangibleinterfaces.datamanage.domain.Characteristic;
import com.tangibleinterfaces.datamanage.domain.ResponseBody;
import com.tangibleinterfaces.datamanage.domain.RolesUser;
import com.tangibleinterfaces.datamanage.domain.TypeCategory;
import com.tangibleinterfaces.datamanage.domain.TypeInfo;
import com.tangibleinterfaces.datamanage.domain.User;
import com.tangibleinterfaces.datamanage.service.CharacteristicService;

@Controller
@RequestMapping(value="/general")
public class GeneralInformationController {

	@Autowired
	CharacteristicService characteristicService;
	
	//list all characteristic basic or complementary
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView generalList() {

			ModelAndView model= new ModelAndView("/general/list");
			model.addObject("list",characteristicService.findAllGeneral());
			
			return model;
		}
		
		//add general from admin
		@RequestMapping(value = "/add", method = RequestMethod.GET)
		public ModelAndView addGeneralForm() {
			ModelAndView model= new ModelAndView("/general/add");
			model.addObject("general", new Characteristic());
			Map<TypeInfo, String> info = new HashMap<TypeInfo, String>();
		        info.put(TypeInfo.BOOL, "Bool");
		        info.put(TypeInfo.LIST, "List");
		        info.put(TypeInfo.TEXT, "Text");
		        info.put(TypeInfo.IMG, "Image");
		        info.put(TypeInfo.PDF, "Pdf");
		        info.put(TypeInfo.LINK, "Link");
		    Map<TypeCategory, String> category = new HashMap<TypeCategory, String>();
		        category.put(TypeCategory.BASIC, "Basic");
		        category.put(TypeCategory.COMPLEMENTARY, "Complementary");
		            
		         
		    model.addObject("infoMap", info);
		    model.addObject("categoryMap", category);
			return model;
		}
		
		@RequestMapping(value = "/add", method = RequestMethod.POST)
		public ModelAndView addGeneral(@Valid Characteristic characteristic, RedirectAttributes redir) {
			if(characteristic.getTypeInfo() != TypeInfo.LIST)
			{
				characteristic.setOptions("");
			}
			String[] options = characteristic.getOptions().split(",");
			int i=0;
			for (String option : options) {
				options[i]=option.trim();
				i++;
			}
			Arrays.sort(options); 
			characteristic.setOptions(String.join(",",options));
			return new ModelAndView("redirect:/general/list","estade",characteristicService.saveCharacteristic(characteristic));
		}
		
		//update general characteristic
		@RequestMapping(value = "/update{id}", method = RequestMethod.GET)
		public ModelAndView  updateGeneralForm(@RequestParam String id) {
			ModelAndView model= new ModelAndView("/general/add");
			model.addObject("general",characteristicService.findByNameGeneral(id));
			Map<TypeInfo, String> info = new HashMap<TypeInfo, String>();
	        info.put(TypeInfo.BOOL, "Bool");
	        info.put(TypeInfo.LIST, "List");
	        info.put(TypeInfo.TEXT, "Text");
	        info.put(TypeInfo.IMG, "Image");
	        info.put(TypeInfo.PDF, "Pdf");
	        info.put(TypeInfo.LINK, "Link");
	    Map<TypeCategory, String> category = new HashMap<TypeCategory, String>();
	        category.put(TypeCategory.BASIC, "Basic");
	        category.put(TypeCategory.COMPLEMENTARY, "Complementary");
	            
	         
	    model.addObject("infoMap", info);
	    model.addObject("categoryMap", category);
			return model;
		}
		
		@RequestMapping(value = "/update{id}", method = RequestMethod.POST)
		public ModelAndView updateGeneral(@Valid Characteristic characteristic, @RequestParam String id) {
			String[] options = characteristic.getOptions().split(",");
			int i=0;
			for (String option : options) {
				options[i]=option.trim();
				i++;
			}
			Arrays.sort(options); 
			characteristic.setOptions(String.join(",",options));
			String result= characteristicService.updateCharacteristic(characteristic,id);
			
			ModelAndView model= new ModelAndView("redirect:/general/list" ,"estade",result);
			
			return model;
		}
		
		//delete characteristic
		@RequestMapping(value = "/delete{id}", method = RequestMethod.GET)
		public ModelAndView deleteGeneral(@RequestParam String id) {
			ModelAndView model= new ModelAndView("redirect:/general/list");
			characteristicService.delete(id);
			return model;

		}
}
