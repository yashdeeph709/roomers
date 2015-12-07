package com.roommanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.roommanagement.services.AdminUserRegistrationService;

@RestController
public class AdminUserDeleteController {
	@Autowired
	private AdminUserRegistrationService registrationService;
	
		@RequestMapping(value="/delete/{id}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody String deletUser(@PathVariable("id") String id) {
			
			
			System.out.println(id);
			registrationService.delete(id);
			return "{\"DONE\":\"YES\"}";
		}

}
