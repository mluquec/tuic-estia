package com.tangibleinterfaces.datamanage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import com.tangibleinterfaces.datamanage.HomeController;
import com.tangibleinterfaces.datamanage.domain.Modification;
import com.tangibleinterfaces.datamanage.domain.ResponseBody;
import com.tangibleinterfaces.datamanage.domain.RolesUser;
import com.tangibleinterfaces.datamanage.domain.User;
import com.tangibleinterfaces.datamanage.service.ModificationService;
import com.tangibleinterfaces.datamanage.service.UserService;

@Controller
@RequestMapping(value="/users")
public class UserController {

	@Autowired
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	UserService userService;
	@Autowired
	ModificationService modificationService;
	//user assign
		@RequestMapping(value = "/assign", method = RequestMethod.GET)
		public ModelAndView asignRequestList() {

			ModelAndView model= new ModelAndView("/users/assign");
			model.addObject("list",modificationService.findAllRequestAssign());
			
			return model;
		}
		//user assign select
				@RequestMapping(value = "/select-moderator{id}", method = RequestMethod.GET)
				public ModelAndView asignRequestModeratorList(@RequestParam("id") String id, @RequestParam("user") String user) {

					ModelAndView model= new ModelAndView("/users/select-moderator");
					Map<String, String> users = new HashMap<String, String>();
					List<User> userList = userService.findModerator();
					for (User userselect : userList) {
						users.put(userselect.getUsername(), userselect.getUsername());
					}
					model.addObject("usersMap", users);
					model.addObject("modification",new Modification());
					
					return model;
				}
				
				
				@RequestMapping(value = "/select-moderator{id}", method = RequestMethod.POST)
				public ModelAndView assignUserModerator(@Valid Modification modification, @RequestParam("id") String id, @RequestParam("user") String user) {
					
					modificationService.asignModerator(user, id, modification.getUserModerator() );
					ModelAndView model= new ModelAndView("redirect:/users/assign");
					
					return model;
				}
				
				
	
	//list all user main user
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView userList() {

		ModelAndView model= new ModelAndView("/users/list");
		model.addObject("list",userService.findAll());
		
		return model;
	}
	
	//profile
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView profile() {
		ModelAndView model= new ModelAndView("/users/add");
		User user= userService.findByUsername(getLoggedInUserName());
		model.addObject("user",user);
		Map<RolesUser, String> roles = new HashMap<RolesUser, String>();
        roles.put(user.getRole(), user.getRole().toString());
        
         
        model.addObject("rolesMap", roles);
		return model;
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public ModelAndView updateUser(@Valid User user) {
		
		String result= userService.updateUser(user,getLoggedInUserName());
		ModelAndView model= new ModelAndView("redirect:/users/profile" ,"estade",result);
		
		return model;
	}
	
	//add user from admin
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addUserForm() {
		ModelAndView model= new ModelAndView("/users/add");
		model.addObject("user", new User());
		Map<RolesUser, String> roles = new HashMap<RolesUser, String>();
	        roles.put(RolesUser.ADMIN, "Administrator");
	        roles.put(RolesUser.MODERATOR, "Moderator");
	        roles.put(RolesUser.USER, "User");
	         
	        model.addObject("rolesMap", roles);
		return model;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addUser(@Valid User user, RedirectAttributes redir) {
		ResponseBody result = new ResponseBody();
		
		boolean estade= userService.saveUser(user);
		
		if(estade)
		{
			result.setMsg("Success");			
		}
		else
		{
			result.setMsg("We can't register your user try again");	
		}
		
		return new ModelAndView("redirect:/users/list","estade",result.getMsg());
	}
	
	
	//delete user
	@RequestMapping(value = "/delete{id}", method = RequestMethod.GET)
	public ModelAndView deleteUser(@RequestParam String id) {
		ModelAndView model= new ModelAndView("redirect:/users/list");
		userService.delete(id);
		return model;

	}
	
	
	//update user
	
	@RequestMapping(value = "/update{id}", method = RequestMethod.GET)
	public ModelAndView  updateUserForm(@RequestParam String id) {
		ModelAndView model= new ModelAndView("/users/add");
		model.addObject("user", userService.findByUsername(id));
		Map<RolesUser, String> roles = new HashMap<RolesUser, String>();
        roles.put(RolesUser.ADMIN, "Administrator");
        roles.put(RolesUser.MODERATOR, "Moderator");
        roles.put(RolesUser.USER, "User");
        model.addObject("rolesMap", roles);
		return model;
	}
	
	@RequestMapping(value = "/update{id}", method = RequestMethod.POST)
	public ModelAndView updateUser(@Valid User user, @RequestParam String id) {
		System.out.println(id);
		String result= userService.updateUser(user,id);
		ModelAndView model= new ModelAndView("redirect:/users/list" ,"estade",result);
		
		return model;
	}
	
	private String getLoggedInUserName() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}
	
	
	////register
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView addUserRegisterForm() {
		ModelAndView model= new ModelAndView("/users/register");
		model.addObject("user", new User());
		
		return model;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView addUserRegister(@Valid User user, RedirectAttributes redir) {
		ResponseBody result = new ResponseBody();
		user.setRole(RolesUser.USER);
		boolean estade= userService.saveUser(user);
		
		if(estade)
		{
			result.setMsg("Success");			
		}
		else
		{
			result.setMsg("We can't register your user try again");	
		}
		
		return new ModelAndView("redirect:/");
	}
}
