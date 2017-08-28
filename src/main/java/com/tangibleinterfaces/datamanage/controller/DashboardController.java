package com.tangibleinterfaces.datamanage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tangibleinterfaces.datamanage.domain.Category;
import com.tangibleinterfaces.datamanage.domain.Characteristic;
import com.tangibleinterfaces.datamanage.domain.Form;
import com.tangibleinterfaces.datamanage.domain.InterfacePlace;
import com.tangibleinterfaces.datamanage.domain.RolesUser;
import com.tangibleinterfaces.datamanage.domain.TangibleCategory;
import com.tangibleinterfaces.datamanage.domain.TangibleCharacteristic;
import com.tangibleinterfaces.datamanage.domain.TangibleInterface;
import com.tangibleinterfaces.datamanage.domain.TypeModification;
import com.tangibleinterfaces.datamanage.domain.User;
import com.tangibleinterfaces.datamanage.service.CategoryService;
import com.tangibleinterfaces.datamanage.service.CharacteristicService;
import com.tangibleinterfaces.datamanage.service.FormService;
import com.tangibleinterfaces.datamanage.service.ModificationService;
import com.tangibleinterfaces.datamanage.service.TangibleService;
import com.tangibleinterfaces.datamanage.service.UserService;


@Controller
@RequestMapping(value="/dashboard")
public class DashboardController {

	@Autowired
	ModificationService modificationService;
	
	@Autowired
	TangibleService tangibleService;
	@Autowired
	UserService userService;
	
	@Autowired
	FormService formService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	CharacteristicService characteristicService;
	
	private String getLoggedInUserName() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}
	
	//dashboard 
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView interfaceList() {

		ModelAndView model= new ModelAndView("/dashboard/list");
		
		User user=userService.findByUsername(getLoggedInUserName());
		
		
		model.addObject("list",modificationService.findMyDashboard(getLoggedInUserName()));
			
		return model;
			
	}
	
	//accept	form
		@RequestMapping(value = "/accept{id}", method = RequestMethod.GET)
		public ModelAndView deleteForm(@RequestParam("id") String id , @RequestParam("user") String user, @RequestParam("version") Integer version,  @RequestParam("type") TypeModification type  ) {
			ModelAndView model= new ModelAndView("redirect:/dashboard/list");
			modificationService.accept(id,user,version,type);
			return model;

		}
		
		@RequestMapping(value = "/deny{id}", method = RequestMethod.GET)
		public ModelAndView denyForm(@RequestParam("id") String id , @RequestParam("user") String user, @RequestParam("version") Integer version,  @RequestParam("type") TypeModification type  ) {
			ModelAndView model= new ModelAndView("redirect:/dashboard/list");
			modificationService.deny(id,user,version,type);
			return model;

		}
		
	
		//view tangible my request
		@RequestMapping(value = "/view{id}", method = RequestMethod.GET)
		public ModelAndView mytangibleDashboardView(@RequestParam("id") String id, @RequestParam("user") String user,  @RequestParam("version") Integer version) {

			ModelAndView model= new ModelAndView("/tangible/view");
			model.addObject("tangible", modificationService.findByPkMyInterfaceVersion(user,id,InterfacePlace.DASHBOARD.toString(),version).getTangible());
			
			return model;
				
			}
		
		//view tangible my request
				@RequestMapping(value = "/parcial{id}", method = RequestMethod.GET)
				public ModelAndView mytangibleDashboardViewParcial(@RequestParam("id") String id, @RequestParam("user") String user,  @RequestParam("version") Integer version) {

					ModelAndView model= new ModelAndView("/dashboard/parcial");
					model.addObject("tangible", new Form());

					model.addObject("tangibleActual", modificationService.findByPkMyInterfaceVersion(user,id,InterfacePlace.DASHBOARD.toString(),version).getTangible());
					
					if(version>1)
					{
						model.addObject("tangiblePublish", tangibleService.findByPk(id));
					}
					else
					{
						model.addObject("tangiblePublish", new TangibleInterface());
					}
					TangibleInterface interfaceModel = modificationService.findByPkMyInterfaceVersion(user,id,InterfacePlace.DASHBOARD.toString(),version).getTangible();
					Map<String, String> categories = new HashMap<String, String>();
					Map<String, String> generals = new HashMap<String, String>();
				    TangibleCharacteristic[] basicList= interfaceModel.getBasic();
				    TangibleCharacteristic[] complementaryList= interfaceModel.getComplementary();
				    TangibleCategory[] categoriesList= interfaceModel.getCategories();
				    for (TangibleCategory category : categoriesList) {
						for (TangibleCharacteristic characteristic : category.getCharacteristics()) {
							
							if(characteristic.getType().equals("LIST"))
							{
								if(characteristic.getValueList()[0].equals(""))
					    		{
									categories.put(category.getName()+"--"+ characteristic.getName()+"--list--"+" --", category.getName()+"-"+ characteristic.getName());

					    		}
								else{
								categories.put(category.getName()+"--"+ characteristic.getName()+"--list--"+String.join(",", characteristic.getValueList())+" --", category.getName()+"-"+ characteristic.getName());
								}
								
								
							}
							else
							{	
							categories.put(category.getName()+"--"+ characteristic.getName()+"--"+characteristic.getType()+"--"+characteristic.getValue()+" --", category.getName()+"-"+ characteristic.getName());
							}
						}
					}
				    for (TangibleCharacteristic basic : basicList) {
				    	if(basic.getType().equals("LIST"))
				    	{
				    		
				    		if(basic.getValueList()[0].equals(""))
				    		{
				    	
				    		generals.put(basic.getName()+"--basic--list--"+" --",basic.getName());
				    		}
				    		else
				    		{	
				    			generals.put(basic.getName()+"--basic--list--"+String.join(",", basic.getValueList())+" --",basic.getName());
				    		}	
				    	}
				    	else
				    	{
				    		generals.put(basic.getName()+"--basic--"+basic.getType()+"--"+basic.getValue()+" --",basic.getName());
				    	}
						
					}    
				    for (TangibleCharacteristic complementary : complementaryList) {
				    	if(complementary.getType().equals("LIST"))
				    	{
				    		if(complementary.getValueList()[0].equals(""))
				    		{
				    			generals.put(complementary.getName()+"--complementary--list--"+" --",complementary.getName());
				    		}
				    		else{
				    		generals.put(complementary.getName()+"--complementary--list--"+String.join(",", complementary.getValueList())+" --",complementary.getName());
				    		}
				    	}
				    	else
				    	{
				    		generals.put(complementary.getName()+"--complementary--"+complementary.getType()+"--"+complementary.getValue()+" --",complementary.getName());
				    	}
					}
				    
				    
				    model.addObject("categoriesMap", categories);
				    model.addObject("generalsMap", generals);
					return model;
						
					}

		
				@RequestMapping(value = "/parcial{id}", method = RequestMethod.POST)
				public ModelAndView parcialSubmitForm(@Valid Form form, @RequestParam("id") String id, @RequestParam("user") String user,  @RequestParam("version") Integer version,  @RequestParam("type") TypeModification type) {
					
					modificationService.acceptParcial(id,user,version,type, form);
					return new ModelAndView("redirect:/dashboard/list");
				}
}
