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
	
	@RequestMapping(value = "/availability/{date}/{roomName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Bookings>> bookingsForDate(@RequestHeader String authToken,@PathVariable("date") String strDate,@PathVariable("roomName") String roomName) throws UnknownHostException, ParseException {
		
		System.out.println("availablity controller str"+strDate);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		Date date = format.parse(strDate);
		System.out.println("formatted date "+date);

		List<Bookings> bookingsList = availabilityService.getBookingsForDate(date,roomName);
		HttpHeaders httpHeaders = new HttpHeaders();

		return new ResponseEntity<List<Bookings>>(bookingsList, httpHeaders,HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/availabilities/{date}/{roomName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Integer,List<Bookings>>> bookingsForDates(@RequestHeader String authToken,@PathVariable("date") String strDate,@PathVariable("roomName") String roomName) throws ParseException, UnknownHostException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		Date date = format.parse(strDate);
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<Map<Integer,List<Bookings>>>(availabilityService.getBookingsForDates(date,roomName),httpHeaders,HttpStatus.ACCEPTED);
	} 

}
