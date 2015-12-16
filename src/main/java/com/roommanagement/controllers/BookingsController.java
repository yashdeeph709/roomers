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
import com.roommanagement.collections.RoomCollection;
import com.roommanagement.collections.UserCollection;
import com.roommanagement.services.AvailabilityServiceImpl;
import com.roommanagement.services.BookingsService;

@CrossOrigin
@RestController
@RequestMapping("/roommanagement")
public class BookingsController {

	@Autowired
	BookingsService bookingservice;

	@Autowired
	private MongoOperations mongoOperations;

	private BasicQuery basicQuery;
	private List<Bookings> bookedRooms;
	private HttpHeaders httpHeaders = new HttpHeaders();

	@RequestMapping(value = "/booking/{roomId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Bookings> requestBooking(@RequestHeader String authToken, @RequestBody Bookings booking,
			@PathVariable("roomId") String roomId) {

		Bookings bookingReturned = null;

		HttpStatus httpStatus = null;

		basicQuery = new BasicQuery("{ \"id\" : \"" + authToken + "\" }");
		UserCollection userCollection = mongoOperations.findOne(basicQuery, UserCollection.class);
		if (userCollection == null) {
			httpStatus = HttpStatus.UNAUTHORIZED;
		} else {
			basicQuery = new BasicQuery("{ \"id\" : \"" + roomId + "\" }");
			RoomCollection roomCollection = mongoOperations.findOne(basicQuery, RoomCollection.class);
			if (roomCollection == null) {
				httpStatus = HttpStatus.BAD_REQUEST;
			} else {
				Room room = new Room(roomCollection);
				booking.setRoom(room);

				bookingReturned = bookingservice.insert(booking, roomId);
				
				if (bookingReturned == null) {
					httpStatus = HttpStatus.BAD_REQUEST;
				} else {
					httpStatus = HttpStatus.CREATED; // If Bookings is created
					
				}
			}
		}

		HttpHeaders httpHeaders = new HttpHeaders();

		return  bookingservice.getStatus(bookingReturned);

	}

	/******** show User Booking **********/

	@RequestMapping(value = "/booking", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Bookings>> getUserBooking(@RequestHeader String authToken) {
		
		return bookingservice.getStatus(bookingservice.getMyBookings(authToken));
	}

	@RequestMapping(value = "/booking/{start}/{end}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Bookings>> getUserBookingRange(@RequestHeader String authToken,
			@PathVariable("start") int start, @PathVariable("end") int end) {

		return bookingservice.getStatus(bookingservice.getMyBookingsRange(authToken, start, end));
	}
	
	/**************Room Allocation*******************/
	@RequestMapping(value = "/booking", method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> allocateRoom(@RequestHeader String authToken,@RequestBody Room room) 
	{
		
			return  bookingservice.getStatus(bookingservice.allocateRoom(room),httpHeaders);
		
	}
	

}
