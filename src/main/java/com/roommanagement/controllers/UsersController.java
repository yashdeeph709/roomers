package com.roommanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.roommanagement.beans.User;
import com.roommanagement.collections.UserCollection;
import com.roommanagement.services.UserService;

@CrossOrigin
@RestController
@RequestMapping("/roommanagement")
public class UsersController {

	HttpStatus httpstatus=null;
	HttpHeaders responseHeaders = new HttpHeaders();
	@Autowired
	private UserService service;
	

	
	@RequestMapping(value="/users", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserCollection>> getUsers(@RequestHeader String authToken) {
		if(service.checkAdmin(authToken)){
			httpstatus=HttpStatus.UNAUTHORIZED;
			return new ResponseEntity<List<UserCollection>>(responseHeaders,httpstatus);
		}
		else
		{
	
			httpstatus=HttpStatus.ACCEPTED;
			return new ResponseEntity<List<UserCollection>>(service.getUsers(),responseHeaders,httpstatus);
		}
		
	}
	@RequestMapping(value="/users/{MongoId}", method=RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> deletUser(@RequestHeader String authToken,@PathVariable("MongoId") String id) {
		
		String status="";
		
		if(service.checkAdmin(authToken))
		{
			httpstatus=HttpStatus.UNAUTHORIZED;
	
		}
		else
		{
		service.delete(id);
		httpstatus=HttpStatus.ACCEPTED;
		}
		System.out.println(status);
		ResponseEntity<String> responseEntity=new ResponseEntity<String>(responseHeaders,httpstatus);
		System.out.println(responseEntity);
		return new ResponseEntity<String>(responseHeaders,httpstatus);
	}
	
		
	@RequestMapping(value="/users", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status<UserCollection> createUser(@RequestBody User user,@RequestHeader String authToken) {

		if(service.checkAdmin(authToken)){
			return new Status<UserCollection>("NotAuthenticated","User not Authenticated");
		}
		else
		{
			
			UserCollection createUserReturnValue= service.insert(new UserCollection(user));
			if(createUserReturnValue==null)
			{
			
				return  new Status<UserCollection>("false","User already exists");
			}
			
			return  new Status<UserCollection>("true","Created User name "+createUserReturnValue.getName());
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
