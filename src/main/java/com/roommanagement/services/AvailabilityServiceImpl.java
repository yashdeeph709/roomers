package com.roommanagement.services;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.roommanagement.beans.Bookings;
import com.roommanagement.repository.BookingsRepository;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {
	
	
	@Autowired
	private BookingsRepository bookingsRepository;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	Date formattedDate;
	
	

	public List<Bookings> getBookingsOfDate(Date date) throws UnknownHostException{
		
		List<Bookings> requiredBookingList = new ArrayList<Bookings>();
		
		requiredBookingList = bookingsRepository.findByStartDateLessThanAndEndDateGreaterThan(date,date);
		
		return requiredBookingList;
	}
	
	
	public Map<Integer,List<Bookings>> bookingsOfRange(Date fromDate) throws ParseException, UnknownHostException{
		
		Map<Integer,List<Bookings>> range = new HashMap<Integer,List<Bookings>>();
		List<Bookings> bookingsList = new ArrayList<Bookings>();
		
		for(int i=0;i<7;i++){
			
			bookingsList = getBookingsOfDate(fromDate);
			range.put(i, bookingsList);
			fromDate.setDate(fromDate.getDate()+1);
			
			
		}
		
		return range;
	}
	
	public HttpStatus getStatus(List<Bookings> bookingsList)
	{
		
		if(bookingsList==null){
			return HttpStatus.NO_CONTENT; 				//If no rooms are there in the db 
		}
		else{
			return HttpStatus.ACCEPTED;					//If room are there
		}
		
	}
		
}
