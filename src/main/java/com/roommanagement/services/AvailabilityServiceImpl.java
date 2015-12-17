package com.roommanagement.services;

import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

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
	
	

	public List<Bookings> getBookingsForDate(Date date,String roomName) throws UnknownHostException{
		
		System.out.println("Date from hit"+date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, +6);
		calendar.add(Calendar.MINUTE, -30);
		
		List<BookingsCollection> requiredBookingList = new ArrayList<BookingsCollection>();
		List<Bookings> bookingsList = new ArrayList<Bookings>();
		//requiredBookingList = bookingsRepository.findByStartDateGreaterThanAndEndDateLessThan(date,date);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");
		//TimeZone tz = TimeZone.getTimeZone("UTC");
		//df.setTimeZone(tz);
		
		String isoDate = df.format(date);
		
		Query query = new Query(Criteria.where("startDate").lte(calendar.getTime()).and("endDate").gte(calendar.getTime()).and("room.roomName").is(roomName));
		System.out.println(query);
		requiredBookingList = mongoOperations.find(query, BookingsCollection.class);
		for(BookingsCollection bookingsCollection:requiredBookingList){
			bookingsList.add(new Bookings(bookingsCollection));
		}
		
		System.out.println(requiredBookingList);
		return bookingsList;
	}
	
	
	public Map<Integer,List<Bookings>> getBookingsForDates(Date fromDate,String roomName) throws ParseException, UnknownHostException{
		
		Map<Integer,List<Bookings>> range = new HashMap<Integer,List<Bookings>>();
		List<Bookings> bookingsList = new ArrayList<Bookings>();
		
		for(int i=0;i<7;i++){
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fromDate);
			calendar.add(Calendar.DATE, i);  
			System.out.println("CAL****"+calendar.getTime());
			bookingsList = getBookingsForDate(calendar.getTime(),roomName);
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
