package com.roommanagement.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
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
import com.roommanagement.beans.Room;
import com.roommanagement.collections.BookingsCollection;
import com.roommanagement.collections.RoomCollection;
import com.roommanagement.beans.Bookings;
import com.roommanagement.services.BookingsService;
import com.roommanagement.services.RoomService;
import com.roommanagement.services.UserService;


@CrossOrigin
@RestController
@RequestMapping("/roommanagement")
public class BookingsController{

	@Autowired
	BookingsService bookingservice;
	
	@Autowired
	private MongoOperations mongoOperations;
	
	@RequestMapping(value = "/bookings/{roomId}", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Bookings> requestBooking(@RequestHeader String authToken,@RequestBody Bookings booking,@PathVariable("roomId") String roomId) {
		
		Bookings bookingReturned = null;
		
		HttpStatus httpStatus = null;
		
		if(roomId==null){
			
			httpStatus = HttpStatus.BAD_REQUEST; 				//If Bookings is not inserted
		}
		else{
		BasicQuery basicQuery= new BasicQuery("{ \"id\" : \""+roomId+"\" }");
		RoomCollection roomCollection=mongoOperations.findOne(basicQuery,RoomCollection.class);
		Room room=new Room(roomCollection);
		booking.setRoom(room);

		bookingReturned =bookingservice.insert(booking);

			if(bookingReturned == null){
				httpStatus = HttpStatus.ALREADY_REPORTED;
			}else{
				httpStatus = HttpStatus.CREATED;					//If Bookings is created
			}
		}

		HttpHeaders httpHeaders = new HttpHeaders();
		System.out.println("**************"+httpStatus);
		return new ResponseEntity<Bookings>(bookingReturned, httpHeaders, httpStatus);
			
	}
	
	

}
