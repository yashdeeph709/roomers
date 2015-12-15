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
import org.springframework.web.bind.annotation.RestController;

import com.roommanagement.beans.Room;
import com.roommanagement.services.RoomService;
import com.roommanagement.services.UserService;


@CrossOrigin
@RestController
@RequestMapping("/roommanagement")
public class RoomController{

	@Autowired
	RoomService roomservice;
	@Autowired
	UserService service;
	HttpStatus httpStatus;
	HttpHeaders httpHeaders = new HttpHeaders();;
	
	@RequestMapping(value = "/room", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Room> addRoom(@RequestHeader String authToken,@RequestBody Room room) {
	
		return roomservice.getStatus(roomservice.insert(room));
			
	}
	
	
	@RequestMapping(value = "/room", method = RequestMethod.GET)
	public ResponseEntity<List<Room>> getRooms(@RequestHeader String authToken) {

		return roomservice.getStatus(roomservice.getRooms());
	}	
	
	
	@RequestMapping(value = "/room/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Room> getRoom(@RequestHeader String authToken,@PathVariable("id") String id) 
	{
	
		return roomservice.getStatus(roomservice.getRoom(id));
			
	}
	
	@RequestMapping(value = "/room", method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateRoom(@RequestHeader String authToken,@RequestBody Room room) 
	{
		
			return  roomservice.getStatus(roomservice.updateRoom(room), httpHeaders, httpStatus);
		
	}
					
	
	@RequestMapping(value="/room/{id}", method=RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteRoom(@RequestHeader String authToken,@PathVariable("id") String id) {

		roomservice.delete(id);
		return  new ResponseEntity<String>("Room details delete successfully",httpHeaders, HttpStatus.ACCEPTED);
	}

	
	@RequestMapping(value="/room/{start}/{end}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Room>> roomRange(@RequestHeader String authToken,@PathVariable("start") int start,@PathVariable("end") int end) {
		
	
		return new ResponseEntity<List<Room>>(roomservice.roomRange(start,end), httpHeaders, HttpStatus.ACCEPTED);

	}
	
}
