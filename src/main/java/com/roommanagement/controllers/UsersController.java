package com.roommanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping("/getUsers")
	public List<UserCollection> getUsers() {
		return service.getUsers();
	}
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status deletUser(@PathVariable("id") String id) {
		service.delete(id);
		return new Status("success","User Deleted Successfully!");
	}

	@RequestMapping(value="/register", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status registerUser(@RequestBody UserCollection user) {

	UserCollection createUserReturnValue= service.insert(new UserCollection(user.getName(), user.getEmail(), 
													user.getPassword(),user.getRights()));

	 return  new Status("true","Created User name ="+createUserReturnValue.getName());
	}
}
