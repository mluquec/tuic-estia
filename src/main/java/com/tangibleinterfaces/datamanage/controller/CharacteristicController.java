package com.tangibleinterfaces.datamanage.controller;


import java.lang.reflect.Array;
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
@RequestMapping(value="/characteristics")
public class CharacteristicController {

	@Autowired
	CharacteristicService characteristicService;
	
	//list all characteristic not basic or complementary
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView characteristicList() {

			ModelAndView model= new ModelAndView("/characteristics/list");
			model.addObject("list",characteristicService.findAll());
			
			return model;
		}
		
		//add characteristic from admin
		@RequestMapping(value = "/add", method = RequestMethod.GET)
		public ModelAndView addCharacteristicForm() {
			ModelAndView model= new ModelAndView("/characteristics/add");
			model.addObject("characteristic", new Characteristic());
			Map<TypeInfo, String> info = new HashMap<TypeInfo, String>();
		        info.put(TypeInfo.BOOL, "Bool");
		        info.put(TypeInfo.LIST, "List");
		        info.put(TypeInfo.TEXT, "Text");
		        
		         
		        model.addObject("infoMap", info);
			return model;
		}
		
		@RequestMapping(value = "/add", method = RequestMethod.POST)
		public ModelAndView addCharacteristic(@Valid Characteristic characteristic, RedirectAttributes redir) {
			
			characteristic.setTypeCategory(TypeCategory.OTHER);
			String[] options = characteristic.getOptions().split(",");
			int i=0;
			for (String option : options) {
				options[i]=option.trim();
				i++;
			}
			Arrays.sort(options); 
			characteristic.setOptions(String.join(",",options));
			
			return new ModelAndView("redirect:/characteristics/list","estade",characteristicService.saveCharacteristic(characteristic));
		}
		
		//update characteristic
		@RequestMapping(value = "/update{id}", method = RequestMethod.GET)
		public ModelAndView  updateCharacteristicForm(@RequestParam String id) {
			ModelAndView model= new ModelAndView("/characteristics/add");
			characteristicService.findByName(id).toString();
			model.addObject("characteristic", characteristicService.findByName(id));
			Map<TypeInfo, String> info = new HashMap<TypeInfo, String>();
	        info.put(TypeInfo.BOOL, "Bool");
	        info.put(TypeInfo.LIST, "List");
	        info.put(TypeInfo.TEXT, "Text");
	        model.addObject("infoMap", info);
			return model;
		}
		
		@RequestMapping(value = "/update{id}", method = RequestMethod.POST)
		public ModelAndView updateCharacteristic(@Valid Characteristic characteristic, @RequestParam String id) {
			characteristic.setTypeCategory(TypeCategory.OTHER);
			String[] options = characteristic.getOptions().split(",");
			int i=0;
			for (String option : options) {
				options[i]=option.trim();
				i++;
			}
			Arrays.sort(options); 
			characteristic.setOptions(String.join(",",options));
			String result= characteristicService.updateCharacteristic(characteristic,id);
			
			ModelAndView model= new ModelAndView("redirect:/characteristics/list" ,"estade",result);
			
			return model;
		}
		
		//delete characteristic
		@RequestMapping(value = "/delete{id}", method = RequestMethod.GET)
		public ModelAndView deleteCharacteristic(@RequestParam String id) {
			ModelAndView model= new ModelAndView("redirect:/characteristics/list");
			characteristicService.delete(id);
			return model;

		}
}
