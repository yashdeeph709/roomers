package com.roommanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.roommanagement.beans.User;
import com.roommanagement.collections.UserCollection;
import com.roommanagement.services.AuthenticationService;

@CrossOrigin
@RestController
@RequestMapping("/roommanagement")
public class AuthenticationController {
	@Autowired
	private AuthenticationService service;
	
	/*
	 * This method will take email,password only in UserCollection Object.
	 * UserBean should be user
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> login(@RequestBody User userbean) {
		
		HttpHeaders responseHeaders = new HttpHeaders();
		User user=service.validate(userbean.getEmail(),userbean.getPassword());
		HttpStatus httpstatus=service.getStatus(user);
		return new ResponseEntity<User>(user, responseHeaders, httpstatus);
	}
}
