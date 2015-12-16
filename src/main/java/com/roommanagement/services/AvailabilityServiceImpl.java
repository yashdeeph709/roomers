package com.roommanagement.services;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.roommanagement.beans.Bookings;
import com.roommanagement.collections.BookingsCollection;
import com.roommanagement.repository.BookingsRepository;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {
	
	
	@Autowired
	private BookingsRepository bookingsRepository;
	
	@Autowired
	private MongoOperations mongoOperations;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	Date formattedDate;
	
	

	public List<Bookings> getBookingsForDate(Date date) throws UnknownHostException{
		
		System.out.println("upar"+date);
		List<BookingsCollection> requiredBookingList = new ArrayList<BookingsCollection>();
		List<Bookings> bookingsList = new ArrayList<Bookings>();
		//requiredBookingList = bookingsRepository.findByStartDateGreaterThanAndEndDateLessThan(date,date);
		Query query = new Query(Criteria.where("startDate").lte(date).and("endDate").gte(date));
		requiredBookingList = mongoOperations.find(query, BookingsCollection.class);
		System.out.println(query);
		for(BookingsCollection bookingsCollection:requiredBookingList){
			bookingsList.add(new Bookings(bookingsCollection));
		}
		
		System.out.println(requiredBookingList);
		return bookingsList;
	}
	
	
	public Map<Integer,List<Bookings>> getBookingsForDates(Date fromDate) throws ParseException, UnknownHostException{
		
		Map<Integer,List<Bookings>> range = new HashMap<Integer,List<Bookings>>();
		List<Bookings> bookingsList = new ArrayList<Bookings>();
		
		for(int i=0;i<7;i++){
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fromDate);
			calendar.add(Calendar.DATE, i);  
			System.out.println(calendar.getTime());
			bookingsList = getBookingsForDate(calendar.getTime());
			range.put(i, bookingsList);
			
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
