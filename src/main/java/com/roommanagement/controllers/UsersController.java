package com.roommanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.roommanagement.beans.Room;
import com.roommanagement.beans.User;
import com.roommanagement.collections.UserCollection;
import com.roommanagement.services.UserService;

@CrossOrigin
@RestController
@RequestMapping("/roommanagement")
public class UsersController {


	@Autowired
	private UserService service;
	
	HttpStatus httpstatus=null;
	HttpHeaders responseHeaders = new HttpHeaders();
	@RequestMapping(value="/user", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserCollection>> getUsers(@RequestHeader String authToken) {
			httpstatus=HttpStatus.ACCEPTED;
			List<UserCollection> users=service.getUsers();
			return new ResponseEntity<List<UserCollection>>(users,responseHeaders,httpstatus);
	}

	@RequestMapping(value="/user/{id}", method=RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> deletUser(@RequestHeader String authToken,@PathVariable("id") String id) {
		service.delete(id);
		httpstatus=HttpStatus.ACCEPTED;
		return new ResponseEntity<String>(responseHeaders,httpstatus);		
	}
	

	
	@RequestMapping(value="/user", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<User> createUser(@RequestHeader String authToken,@RequestBody User user) {
				return service.getStatus(service.insert(new UserCollection(user)));
	}
	
	@RequestMapping(value="/user/{start}/{end}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> roomRange(@RequestHeader String authToken,@PathVariable("start") int start,@PathVariable("end") int end) {
		
	
		return new ResponseEntity<List<User>>(service.userRange(start, end), responseHeaders, HttpStatus.ACCEPTED);

	}
}
