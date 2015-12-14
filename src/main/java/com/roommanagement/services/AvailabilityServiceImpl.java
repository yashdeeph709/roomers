package com.roommanagement.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Service;

import com.roommanagement.beans.Bookings;
import com.roommanagement.beans.Room;
import com.roommanagement.collections.BookingsCollection;
import com.roommanagement.collections.RoomCollection;
import com.roommanagement.repository.BookingsRepository;
import com.roommanagement.repository.RoomRepository;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {

	@Autowired
	private BookingsRepository bookingsRepository;
	
	@Autowired
	private MongoOperations mongoOperations;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	Date formattedDate;

	public List<Bookings> getBookingsOfDate(Date date){
		/*
		formattedDate = date;
		System.out.println(formattedDate);*/
		
		BasicQuery basicQuery= new BasicQuery("{\"startDate\": lte:{"+date+"},{\"endDate\": gte:{"+date+"}}");
		List<BookingsCollection> allBookings = mongoOperations.find(basicQuery,BookingsCollection.class);
		/*List<BookingsCollection> allBookings = bookingsRepository.findAll();
		List<Bookings> selectedBookings = new ArrayList<Bookings>();
		for(BookingsCollection bc: allBookings){
			if(bc.getStartDate().compareTo(date)==-1 && bc.getEndDate().compareTo(date)==1)
			{
				selectedBookings.add(new Bookings(bc));
			}
		}
		return selectedBookings;*/
		return null;
	}
	
	
	public List<Bookings> bookingsOfRange(Date fromDate,Date toDate){
		
		return null;
	}
		
}
