package com.roommanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.roommanagement.beans.Status;
import com.roommanagement.collections.UserCollection;
import com.roommanagement.services.UserService;

@CrossOrigin
@RestController
@RequestMapping("/RoomManagement")
public class UsersController {

	
	@Autowired
	private UserService service;
	
	@RequestMapping(value="/getUsers", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public Status<UserCollection> getUsers(@RequestHeader String authToken) {
		if(service.checkAdmin(authToken)){
			return new Status<UserCollection>("NotAuthenticated","User not Authenticated");
		}
		return new Status<UserCollection>("success","got data",service.getUsers());
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status<UserCollection> deletUser(@RequestHeader String authToken,@PathVariable("id") String id) {
		if(service.checkAdmin(authToken)){
			return new Status<UserCollection>("NotAuthenticated","User not Authenticated");
		}
		service.delete(id);
		return new Status<UserCollection>("success","User Deleted Successfully!");
	}
		
	@RequestMapping(value="/register", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status<UserCollection> registerUser(@RequestBody UserCollection user,@RequestHeader String authToken) {
	UserCollection createUserReturnValue= service.insert(new UserCollection(user.getName(), user.getEmail(), 
													user.getPassword(),user.getRights()));
		if(service.checkAdmin(authToken)){
			return new Status<UserCollection>("NotAuthenticated","User not Authenticated");
		}
		return  new Status<UserCollection>("true","Created User name ="+createUserReturnValue.getName());
	}

	@RequestMapping(value="/login", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status<UserCollection> login(@RequestBody UserCollection user) {
		if(service.validate(user.getEmail(),user.getPassword())){
			UserCollection userobject=service.getUser(user.getEmail());
			userobject.setPassword(null);
			return new Status<UserCollection>("Success","User loggedIn successfully",userobject);
		}else{
			return new Status<UserCollection>("failed","User Doesn't exist");
		}
	}

	
	/*
	 * Note:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::;
	 * the are services below are introduced for testing purposes please don't consider them for code review
	 */
	@RequestMapping("/getAdmin")
	public @ResponseBody Status<UserCollection> getAdmin() {
		String id=service.getAdmin();
		return new Status<UserCollection>("id",id);
	}
	@RequestMapping("/getUser")
	public @ResponseBody Status<UserCollection> getUser() {
		String id=service.getUser();
		return new Status<UserCollection>("id",id);
	}
	@RequestMapping("/getSubAdmin")
	public @ResponseBody Status<UserCollection> getSubAdmin() {
		String id=service.getSubAdmin();
		return new Status<UserCollection>("id",id);
	}

}
