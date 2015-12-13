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

import com.roommanagement.beans.Bookings;
import com.roommanagement.beans.Bookings;
import com.roommanagement.services.RoomService;
import com.roommanagement.services.UserService;


@CrossOrigin
@RestController
@RequestMapping("/roommanagement")
public class BookingsController{

	@Autowired
	RoomService roomservice;
	@Autowired
	UserService service;
	
	
	@RequestMapping(value = "/bookings", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Bookings> addRooms(@RequestHeader String authToken,@RequestBody Bookings booking) {
		
		Bookings roomReturned = null;
		
		HttpStatus httpStatus = null;
		
		if(Bookings.getRoomName()==null || Bookings.getRoomLocation()==null || Bookings.getRoomBlock()==null || Bookings.getRoomCity()==null || Bookings.getRoomAddress()==null){
			
			httpStatus = HttpStatus.BAD_REQUEST; 				//If Bookings is not inserted
		}
		else{
			roomReturned =roomservice.insert(booking);
			if(roomReturned == null){
				httpStatus = HttpStatus.ALREADY_REPORTED;
			}else{
				httpStatus = HttpStatus.CREATED;					//If Bookings is created
			}
		}
		
		
		HttpHeaders httpHeaders = new HttpHeaders();

		return new ResponseEntity<Bookings>(roomReturned, httpHeaders, httpStatus);
			
	}
	
	
//	@RequestMapping(value = "/rooms", method = RequestMethod.GET)
//	public ResponseEntity<List<Bookings>> getRooms(@RequestHeader String authToken) {
//		if(service.checkAdmin(authToken)){
//			
//			//return new Status<RoomCollection>("NotAuthenticated","User not Authenticated");
//		}
//		
//		HttpStatus httpStatus = null;
//		
//		List<Bookings> roomList = roomservice.getRooms();
//		
//		
//		if(roomList==null){
//			httpStatus = HttpStatus.NO_CONTENT; 				//If no rooms are there in the db 
//		}
//		else{
//			httpStatus = HttpStatus.ACCEPTED;					//If Bookings are there
//		}
//		
//		HttpHeaders httpHeaders = new HttpHeaders();
//		
//		return new ResponseEntity<List<Bookings>>(roomList, httpHeaders, httpStatus);
//	}	
//	
//	
//	@RequestMapping(value = "/rooms/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Bookings> getRoom(@RequestHeader String authToken,@PathVariable("id") String id) 
//	{
//		HttpStatus httpStatus = null;
//		
//		Bookings requiredRoom = roomservice.getRoom(id);
//		
//		if(requiredRoom==null){
//			httpStatus = HttpStatus.NOT_FOUND; 				//If no rooms are there in the db 
//		}
//		else{
//			httpStatus = HttpStatus.FOUND;					//If Bookings are there
//		}
//		
//		HttpHeaders httpHeaders = new HttpHeaders();
//
//		return new ResponseEntity<Bookings>(requiredRoom, httpHeaders, httpStatus);
//			
//	}
//	
//	@RequestMapping(value = "/rooms", method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<String> updateRoom(@RequestHeader String authToken,@RequestBody Bookings Bookings) {
//		
//		HttpHeaders httpHeaders = new HttpHeaders();
//		
//		if(service.checkAdmin(authToken)){
//			//return new Status<RoomCollection>("NotAuthenticated","User not Authenticated");
//		}
//		if(Bookings.getRoomName()==null||Bookings.getRoomBlock()==null||Bookings.getRoomLocation()==null){
//			
//			return  new ResponseEntity<String>("Required fields could not be empty", httpHeaders, HttpStatus.PRECONDITION_REQUIRED);
//		}
//		else{
//		
//			roomservice.updateRoom(Bookings);
//			return  new ResponseEntity<String>("Bookings details updated successfully", httpHeaders, HttpStatus.ACCEPTED);
//		}
//	}
//					
//	
//	@RequestMapping(value="/rooms/{id}", method=RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<String> deletRoom(@RequestHeader String authToken,@PathVariable("id") String id) {
//		roomservice.delete(id);
//		HttpHeaders httpHeaders = new HttpHeaders();
//		return  new ResponseEntity<String>(httpHeaders, HttpStatus.ACCEPTED);
//	}
//	
//	@RequestMapping(value="/rooms/{start}/{end}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<List<Bookings>> roomRange(@RequestHeader String authToken,@PathVariable("start") int start,@PathVariable("end") int end) {
//		
//		HttpHeaders httpHeaders = new HttpHeaders();
//
//		return new ResponseEntity<List<Bookings>>(roomservice.roomRange(start,end), httpHeaders, HttpStatus.FOUND);
//
//	}
	
}
