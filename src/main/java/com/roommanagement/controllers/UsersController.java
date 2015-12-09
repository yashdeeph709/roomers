package com.roommanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@RequestMapping("/getUsers/{id}")
	public Status<UserCollection> getUsers(@PathVariable String id) {
		if(service.checkUser(id)){
			return new Status<UserCollection>("NotAuthenticated","User not Authenticated");
		}
		return new Status<UserCollection>("success","got data",service.getUsers());
	}
	
	
	
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status<UserCollection> deletUser(@PathVariable("id") String id) {
		if(service.checkUser(id)){
			return new Status<UserCollection>("NotAuthenticated","User not Authenticated");
		}
		service.delete(id);
		return new Status<UserCollection>("success","User Deleted Successfully!");
	}
	
	
	
	
	

	@RequestMapping(value="/register/{id}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status<UserCollection> registerUser(@RequestBody UserCollection user,@PathVariable("id") String id) {
	UserCollection createUserReturnValue= service.insert(new UserCollection(user.getName(), user.getEmail(), 
													user.getPassword(),user.getRights()));
		if(service.checkUser(id)){
			return new Status<UserCollection>("NotAuthenticated","User not Authenticated");
		}
		return  new Status<UserCollection>("true","Created User name ="+createUserReturnValue.getName());
	}
}
