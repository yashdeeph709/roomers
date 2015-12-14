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
import com.roommanagement.services.AvailabilityServiceImpl;
import com.roommanagement.services.BookingsService;


@CrossOrigin
@RestController
@RequestMapping("/roommanagement")
public class BookingsController{

	@Autowired
	BookingsService bookingservice;
	
	@Autowired
	private MongoOperations mongoOperations;
	HttpStatus httpStatus;
	HttpHeaders httpHeaders;
	

	@RequestMapping(value = "/booking/{roomId}", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Bookings> requestBooking(@RequestHeader String authToken,@RequestBody Bookings booking,@PathVariable("roomId") String roomId) {
		httpHeaders = new HttpHeaders();	
		Bookings bookingReturned=bookingservice.insert(booking,roomId);
		httpStatus=bookingservice.getStatus(bookingReturned);
		return new ResponseEntity<Bookings>(bookingReturned, httpHeaders, httpStatus);
	}
	
	
	
/******** show User Booking**********/
	
	@RequestMapping(value = "/booking", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Bookings>> getUserBooking(@RequestHeader String authToken) {

		List<Bookings> bookings = bookingservice.getMyBookings(authToken);
		httpHeaders = new HttpHeaders();
		httpStatus=bookingservice.getStatus(bookings);
		return new ResponseEntity<List<Bookings>>(bookings, httpHeaders, httpStatus);
	}
                                   
	@RequestMapping(value = "/booking/{start}/{end}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Bookings>> getUserBookingRange(@RequestHeader String authToken,
			@PathVariable("start") int start, @PathVariable("end") int end) {

		List<Bookings> bookings = bookingservice.getMyBookingsRange(authToken, start, end);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpStatus=bookingservice.getStatus(bookings);
		return new ResponseEntity<List<Bookings>>(bookings, httpHeaders, httpStatus);
	}
	
	

}
