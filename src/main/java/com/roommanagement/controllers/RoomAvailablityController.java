package com.roommanagement.controllers;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
import com.roommanagement.beans.Bookings;
import com.roommanagement.services.AvailabilityService;

@CrossOrigin
@RestController
@RequestMapping("/roommanagement")
public class RoomAvailablityController {
	
	@Autowired
	AvailabilityService availabilityService;
	
	@RequestMapping(value = "/availability/{date}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Bookings>> bookingsOfDate(@RequestHeader String authToken,@PathVariable("date") String strDate) throws UnknownHostException, ParseException {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date date = format.parse(strDate);
		List<Bookings> bookingsList = availabilityService.getBookingsOfDate(date);
		HttpHeaders httpHeaders = new HttpHeaders();

		return new ResponseEntity<List<Bookings>>(bookingsList, httpHeaders,HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/availabilities/{date}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Integer,List<Bookings>>> bookingsOfRange(@RequestHeader String authToken,@PathVariable("date") String strDate) throws ParseException, UnknownHostException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date date = format.parse(strDate);
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<Map<Integer,List<Bookings>>>(availabilityService.bookingsOfRange(date),httpHeaders,HttpStatus.ACCEPTED);
	} 

}
