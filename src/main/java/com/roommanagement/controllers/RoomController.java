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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.roommanagement.beans.Room;
import com.roommanagement.beans.Status;
import com.roommanagement.collections.RoomCollection;
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
	
	
	@RequestMapping(value = "/rooms", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Room> addRooms(@RequestBody Room room,@RequestHeader String authToken) {
		
		if(service.checkAdmin(authToken)){
			
			//return new Status<RoomCollection>("NotAuthenticated","User not Authenticated");
			
		}
		
		HttpStatus httpStatus = null;
		
		Room roomReturned =roomservice.insert(room);
		
		if(roomReturned==null){
			httpStatus = HttpStatus.BAD_REQUEST; 				//If room is not inserted
		}
		else{
			httpStatus = HttpStatus.CREATED;					//If room is created
		}
		
		HttpHeaders httpHeaders = new HttpHeaders();

		return new ResponseEntity<Room>(roomReturned, httpHeaders, httpStatus);
			
	}
	
	
	@RequestMapping(value = "/rooms", method = RequestMethod.GET)
	public ResponseEntity<List<Room>> getRooms(@RequestHeader String authToken) {
		if(service.checkAdmin(authToken)){
			
			//return new Status<RoomCollection>("NotAuthenticated","User not Authenticated");
		}
		
		HttpStatus httpStatus = null;
		
		List<Room> roomList = roomservice.getRooms();
		
		
		if(roomList==null){
			httpStatus = HttpStatus.NO_CONTENT; 				//If no rooms are there in the db 
		}
		else{
			httpStatus = HttpStatus.FOUND;					//If room are there
		}
		
		HttpHeaders httpHeaders = new HttpHeaders();
		
		return new ResponseEntity<List<Room>>(roomList, httpHeaders, httpStatus);
	}	
	
	
	@RequestMapping(value = "/rooms/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Room> getRoom(@RequestHeader String authToken,@PathVariable("id") String id) 
	{
		if(service.checkAdmin(authToken)){
			
			//return new Status<RoomCollection>("NotAuthenticated","User not Authenticated");
		}
		
		HttpStatus httpStatus = null;
		
		Room requiredRoom = roomservice.getRoom(id);
		
		if(requiredRoom==null){
			httpStatus = HttpStatus.NOT_FOUND; 				//If no rooms are there in the db 
		}
		else{
			httpStatus = HttpStatus.FOUND;					//If room are there
		}
		
		HttpHeaders httpHeaders = new HttpHeaders();

		return new ResponseEntity<Room>(requiredRoom, httpHeaders, httpStatus);
			
	}
	
	@RequestMapping(value = "/rooms", method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateRoom(@RequestBody Room room,@RequestHeader String authToken) {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		
		if(service.checkAdmin(authToken)){
			//return new Status<RoomCollection>("NotAuthenticated","User not Authenticated");
		}
		if(room.getRoomName()==null||room.getRoomBlock()==null||room.getRoomLocation()==null){
			
			return  new ResponseEntity<String>("Required fields could not be empty", httpHeaders, HttpStatus.PRECONDITION_REQUIRED);
		}
		else{
		
			roomservice.updateRoom(room);
			return  new ResponseEntity<String>("Room details updated successfully", httpHeaders, HttpStatus.ACCEPTED);
		}
	}
					
	
	@RequestMapping(value="/rooms/{id}", method=RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deletRoom(@PathVariable("id") String id) {
		roomservice.delete(id);
		HttpHeaders httpHeaders = new HttpHeaders();
		return  new ResponseEntity<String>("Room details updated successfully", httpHeaders, HttpStatus.ACCEPTED);
	}
	
	/*
	@RequestMapping(value="/availRoomName/{roomName}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public Status<RoomCollection> checkRoom(@PathVariable("roomName") String roomName,@RequestHeader String authToken) {
		if(service.checkAdmin(authToken)){
			return new Status<RoomCollection>("NotAuthenticated","User not Authenticated");
		}
		Boolean available = roomservice.checkRoomNameAvailablility(roomName);
		if(available)
			return  new Status<RoomCollection>("true","Room of this name is not there");
		else
			return  new Status<RoomCollection>("false","Room of this name is already there");
	}
	*/
	@RequestMapping("/getRoomsCount")
	public Status getRoomsCount() {
		
		return roomservice.getRoomCount();
	}
}
