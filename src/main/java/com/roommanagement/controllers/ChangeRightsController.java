package com.roommanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.roommanagement.beans.User;
import com.roommanagement.services.ChangeRightsService;

@CrossOrigin
@RestController
@RequestMapping("/roommanagement")
public class ChangeRightsController {

		
	@Autowired
	ChangeRightsService changeRightsService; 

	@RequestMapping(value = "/rights/{id}", method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<User> changeUserRightsToSubAdmin(@RequestHeader String authToken,@PathVariable("id") String id) {
		System.out.println(id);
		
		return changeRightsService.updateRightsToSubAdmin(id);
	}
	
	@RequestMapping(value = "/rights/{id}", method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<User> changeSubRightsToUser(@RequestHeader String authToken,@PathVariable("id") String id) {
		System.out.println(id);
		return changeRightsService.updateRightsToUser(id);
	}

}
