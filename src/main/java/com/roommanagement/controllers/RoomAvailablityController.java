package com.roommanagement.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import com.roommanagement.services.AvailabilityService;

@CrossOrigin
@RestController
@RequestMapping("/roommanagement")
public class RoomAvailablityController {
	
	@Autowired
	AvailabilityService availabilityService;
	
	@RequestMapping(value = "/availability", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Bookings>> getBookingOfDate(@RequestHeader String authToken,@RequestBody Map<String,String> dateStr) throws ParseException {
		
		
		HttpStatus httpStatus = null;
		
		List<Bookings> bookingsList = availabilityService.getBookingsOfDate(dateStr.get("date"));
		
		
		if(bookingsList==null){
			httpStatus = HttpStatus.NO_CONTENT; 				//If no rooms are there in the db 
		}
		else{
			httpStatus = HttpStatus.ACCEPTED;					//If room are there
		}
		
		HttpHeaders httpHeaders = new HttpHeaders();
		
		return new ResponseEntity<List<Bookings>>(bookingsList, httpHeaders, httpStatus);
	}	

}
