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
			httpstatus=HttpStatus.ACCEPTED;
			List<UserCollection> users=service.getUsers();
			System.out.println(users);
			return new ResponseEntity<List<UserCollection>>(users,responseHeaders,httpstatus);
	}
	@RequestMapping(value="/users/{MongoId}", method=RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> deletUser(@PathVariable("MongoId") String id,@RequestHeader String authToken) {
		service.delete(id);
		httpstatus=HttpStatus.ACCEPTED;
		ResponseEntity<String> responseEntity=new ResponseEntity<String>(responseHeaders,httpstatus);
		return new ResponseEntity<String>(responseHeaders,httpstatus);
	}
	

	
	@RequestMapping(value="/users", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<UserCollection> createUser(@RequestBody User user,@RequestHeader String authToken) {
			UserCollection createUserReturnValue= service.insert(new UserCollection(user));
			if(createUserReturnValue==null)
			{
				httpstatus=HttpStatus.ALREADY_REPORTED;
				return new ResponseEntity<UserCollection>(null,responseHeaders,httpstatus);
			}
			httpstatus=HttpStatus.CREATED;
			return new ResponseEntity<UserCollection>(createUserReturnValue,responseHeaders,httpstatus);
	}
}
