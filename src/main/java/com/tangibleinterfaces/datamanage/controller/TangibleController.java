package com.tangibleinterfaces.datamanage.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tangibleinterfaces.datamanage.HomeController;
import com.tangibleinterfaces.datamanage.domain.Category;
import com.tangibleinterfaces.datamanage.domain.Characteristic;
import com.tangibleinterfaces.datamanage.domain.Form;
import com.tangibleinterfaces.datamanage.domain.InterfacePlace;
import com.tangibleinterfaces.datamanage.domain.Modification;
import com.tangibleinterfaces.datamanage.domain.MultipleSelect;
import com.tangibleinterfaces.datamanage.domain.StadeModification;
import com.tangibleinterfaces.datamanage.domain.TangibleCharacteristic;
import com.tangibleinterfaces.datamanage.domain.TangibleInterface;
import com.tangibleinterfaces.datamanage.domain.TypeModification;
import com.tangibleinterfaces.datamanage.service.FormService;
import com.tangibleinterfaces.datamanage.service.ModificationService;
import com.tangibleinterfaces.datamanage.service.TangibleService;

@Controller
@RequestMapping(value="/tangible")

public class TangibleController {
	@Autowired
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	TangibleService tangibleService;
	@Autowired
	FormService formService;
	@Autowired
	ModificationService modificationService;
	
	
	//list all interfaces published 
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView interfaceList() {

		ModelAndView model= new ModelAndView("/tangible/list");
		
		
		model.addObject("list",tangibleService.findAll());
			
		return model;
			
	}
	
	//list all interfaces published admin
		@RequestMapping(value = "/list-admin", method = RequestMethod.GET)
		public ModelAndView interfaceListAdmin() {

			ModelAndView model= new ModelAndView("/tangible/list-admin");
			
			
			model.addObject("list",tangibleService.findAll());
				
			return model;
				
		}
	//list all interfaces my interfaces
		@RequestMapping(value = "/my-interfaces", method = RequestMethod.GET)
		public ModelAndView myInterfacesList() {

			ModelAndView model= new ModelAndView("/tangible/my-interfaces");
			
			
			model.addObject("list",modificationService.findAllMyInterfaces(getLoggedInUserName(),InterfacePlace.EDITOR ));
				
			return model;
				
		}
		
		
	
	
	
	//add tangible form my  interfaces
		@RequestMapping(value = "/add", method = RequestMethod.GET)
		public ModelAndView addTangibleForm() {
			ModelAndView model= new ModelAndView("/tangible/add");
			model.addObject("tangible", tangibleService.getModel());
		    return model;
		}
		
		
		@RequestMapping(value = "/add", method = RequestMethod.POST)
		public ModelAndView addTangible(@Valid TangibleInterface tangible, RedirectAttributes redir) {
			
			Modification modification = new Modification();
			modification.setInterfacePlace(InterfacePlace.EDITOR);
			modification.setUser(getLoggedInUserName());
			modification.setDateModification(new Date());
			modification.setVersion(1);
			TangibleCharacteristic[] basic= tangible.getBasic();
			String pkName= formService.getPk();
			for (TangibleCharacteristic tangibleCharacteristic : basic) {
				if(tangibleCharacteristic.getName().equals(pkName))
				{
					tangible.setPk(tangibleCharacteristic.getValue());
				}
			}
			
			modification.setTangible(tangibleService.getNewOptions(tangible));
			modificationService.save(modification);
			return new ModelAndView("redirect:/tangible/my-interfaces");
		}
		
	//view tangible 
 
		@RequestMapping(value = "/view{id}", method = RequestMethod.GET)
		public ModelAndView tangibleView(@RequestParam String id) {

			ModelAndView model= new ModelAndView("/tangible/view");
			model.addObject("tangible", tangibleService.findByPk(id));
			
			return model;
				
			}
		//view tangible my interface
		@RequestMapping(value = "/my-interfaceview{id}", method = RequestMethod.GET)
		public ModelAndView mytangibleView(@RequestParam String id) {

			ModelAndView model= new ModelAndView("/tangible/view");
			model.addObject("tangible", modificationService.findByPkMyInterface(getLoggedInUserName(),id,InterfacePlace.EDITOR.toString()));
			
			return model;
				
			}
		
		
		//delete 	tangible my onterfaces
		@RequestMapping(value = "/delete{id}", method = RequestMethod.GET)
		public ModelAndView deleteTangible(@RequestParam String id) {
			ModelAndView model= new ModelAndView("redirect:/tangible/my-interfaces");
			modificationService.deleteMyInterface(getLoggedInUserName(),id,InterfacePlace.EDITOR.toString());
			return model;

		}
		
		//delete 	tangible my onterfaces admin
				@RequestMapping(value = "/delete-admin{id}", method = RequestMethod.GET)
				public ModelAndView deleteTangibleAdmin(@RequestParam String id) {
					ModelAndView model= new ModelAndView("redirect:/tangible/list-admin");
					tangibleService.delete(id);
					return model;

				}
				
				
				//update tangible my interfaces
		@RequestMapping(value = "/update{id}", method = RequestMethod.GET)
				public ModelAndView  updateTangibleForm(@RequestParam String id) {
					ModelAndView model= new ModelAndView("/tangible/add");

					

					model.addObject("tangible", tangibleService.createModelMixed(modificationService.findByPkMyInterface(getLoggedInUserName(),id,InterfacePlace.EDITOR.toString())));

				    return model;
		}
		@RequestMapping(value = "/update{id}", method = RequestMethod.POST)
		public ModelAndView updateTangible(@Valid TangibleInterface tangible, @RequestParam String id) {

			Modification modification = new Modification();
			modification.setInterfacePlace(InterfacePlace.EDITOR);
			modification.setUser(getLoggedInUserName());
			modification.setDateModification(new Date());
			modification.setVersion(1);
			TangibleCharacteristic[] basic= tangible.getBasic();
			String pkName= formService.getPk();
			for (TangibleCharacteristic tangibleCharacteristic : basic) {
				if(tangibleCharacteristic.getName().equals(pkName))
				{
					tangible.setPk(tangibleCharacteristic.getValue());
				}
			}
			modification.setTangible(tangibleService.getNewOptions(tangible));
			String result= modificationService.updateModificationMyInterface(modification, id );
			
			ModelAndView model= new ModelAndView("redirect:/tangible/my-interfaces" ,"estade",result);
			
			return model;
		}
				
		//update tangible my interfaces admin
		@RequestMapping(value = "/update-admin{id}", method = RequestMethod.GET)
		public ModelAndView  updateTangibleFormAdmin(@RequestParam String id) {
			ModelAndView model= new ModelAndView("/tangible/add");

			

			model.addObject("tangible", tangibleService.createModelMixed(tangibleService.findByPk(id)));

		    return model;
		}
		
		@RequestMapping(value = "/update-admin{id}", method = RequestMethod.POST)
		public ModelAndView updateTangibleAdmin(@Valid TangibleInterface tangible, @RequestParam String id) {

			Modification modification = modificationService.getLastVersion(id,InterfacePlace.UPLOADS);
			
			
			TangibleCharacteristic[] basic= tangible.getBasic();
			String pkName= formService.getPk();
			for (TangibleCharacteristic tangibleCharacteristic : basic) {
				if(tangibleCharacteristic.getName().equals(pkName))
				{
					tangible.setPk(tangibleCharacteristic.getValue());
				}
			}
			
			modification.setTangible(tangibleService.getNewOptions(tangible));
			
			String result= modificationService.updateModificationMyInterfaceAdmin(modification, id );
			
			ModelAndView model= new ModelAndView("redirect:/tangible/list-admin" ,"estade",result);
			
			return model;
		}
		
		private String getLoggedInUserName() {
			Object principal = SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();

			if (principal instanceof UserDetails)
				return ((UserDetails) principal).getUsername();

			return principal.toString();
		}
		
		
		
		///--------------------------------------------------------------------------------------
		
		//*/ my requests ---
		//update tangible my interfaces
				@RequestMapping(value = "/review{id}", method = RequestMethod.GET)
				public ModelAndView  uploadReviewTangibleForm(Locale locale, @RequestParam String id) {
					
					Modification modification = new Modification();
					Date date = new Date();
					modification.setDateModification(date);
					modification.setInterfacePlace(InterfacePlace.ASIGNED);
					modification.setTypeModification(TypeModification.NEW);
					modification.setVersion(1);
					modification.setUser(getLoggedInUserName());
					modification.setTangible(modificationService.findByPkMyInterface(getLoggedInUserName(),id,InterfacePlace.EDITOR.toString()));
					
					modificationService.updateModificationEditor(getLoggedInUserName(),id,InterfacePlace.EDITOR.toString());
				
					ModelAndView model= new ModelAndView("redirect:/tangible/my-request","estade", modificationService.uploadInterface(modification));
				    return model;
				}
		
				//my request
				@RequestMapping(value = "/my-request", method = RequestMethod.GET)
				public ModelAndView myRequestList() {

					ModelAndView model= new ModelAndView("/tangible/my-request");
					
					
					model.addObject("list",modificationService.findMyRequest(getLoggedInUserName(),InterfacePlace.ASIGNED.toString() ));
						
					return model;
						
				}
				
		
				//view tangible my request
				@RequestMapping(value = "/my-requestview{id}", method = RequestMethod.GET)
				public ModelAndView myrequestView(@RequestParam String id) {

					ModelAndView model= new ModelAndView("/tangible/view");
					model.addObject("tangible", modificationService.findByPkMyInterface(getLoggedInUserName(),id,InterfacePlace.ASIGNED.toString()));
					
					return model;
						
					}
				
				
				
				//----------my uploads

				@RequestMapping(value = "/my-upload", method = RequestMethod.GET)
				public ModelAndView myUploadsList() {

					ModelAndView model= new ModelAndView("/tangible/my-upload");
					
					
					model.addObject("list",modificationService.findMyRequest(getLoggedInUserName(),InterfacePlace.UPLOADS.toString() ));
						
					return model;
						
				}
				//view tangible my request
				@RequestMapping(value = "/my-uploadview{id}", method = RequestMethod.GET)
				public ModelAndView mytangibleDashboardView(@RequestParam("id") String id, @RequestParam("user") String user, @RequestParam("version") Integer version ) {

					ModelAndView model= new ModelAndView("/tangible/view");
					model.addObject("tangible", modificationService.findByPkMyInterfaceVersion(user,id,InterfacePlace.UPLOADS.toString(),version).getTangible());
					
					return model;
						
					}
				
				@RequestMapping(value = "/my-uploadedit{id}", method = RequestMethod.GET)
				public ModelAndView mytangibleEditFormDashboard(@RequestParam("id") String id, @RequestParam("user") String user, @RequestParam("version") Integer version ) {

					ModelAndView model= new ModelAndView("/tangible/add");
					model.addObject("tangible", tangibleService.createModelMixed(modificationService.findByPkMyInterfaceVersion(user,id,InterfacePlace.UPLOADS.toString(),version).getTangible()));

				
					return model;
						
					}
				
				@RequestMapping(value = "/my-uploadedit{id}", method = RequestMethod.POST)
				public ModelAndView mytangibleEditDashboard(@Valid TangibleInterface tangible, @RequestParam("id") String id, @RequestParam("user") String user, @RequestParam("version") Integer version ) {
					Modification modification = modificationService.findByPkMyInterfaceVersion(user,id,InterfacePlace.UPLOADS.toString(),version);
					modification.setTypeModification(TypeModification.EDIT);
					tangible.setPk(id);
					modification.setTangible(tangibleService.getNewOptions(tangible));
					modification.setIsActive(false);
					modification.setDateModification(new Date());
					modification.setStadeModification(StadeModification.OTHER);
					
					
					String result =modificationService.updateUpload(modification);
					ModelAndView model= new ModelAndView("redirect:/tangible/my-upload","estade",result);
					
					
					return model;
						
					}
				
				//upload a new version 
				
				@RequestMapping(value = "/my-uploadrequest{id}", method = RequestMethod.GET)
				public ModelAndView myeditUploadTangible(@RequestParam("id") String id, @RequestParam("user") String user, @RequestParam("version") Integer version) {
					String result= modificationService.uploadRequestEdit(id,user,version);
					ModelAndView model= new ModelAndView("redirect:/tangible/my-upload","estade",result);
					
					return model;

				}
				
			//------------------------ multiple edit 
				
				@RequestMapping(value = "/multiple-update{place}", method = RequestMethod.GET)
				public ModelAndView myeditUploadTangible(@RequestParam("place") InterfacePlace place) {
					
					ModelAndView model= new ModelAndView("/tangible/multiple-edit");
					List<Modification> modifications=modificationService.findAllMyInterfaces(getLoggedInUserName(),place );
					MultipleSelect tangibles = new MultipleSelect();
					String[] tangibleTemp = new String[modifications.size()];
					int i=0;
					for (Modification modification : modifications) {
						tangibleTemp[i]=modification.getTangible().getPk();
					}
					tangibles.setObjectSelect(tangibleTemp);
					model.addObject("tangible", tangibles);
					return model;

				}
}
