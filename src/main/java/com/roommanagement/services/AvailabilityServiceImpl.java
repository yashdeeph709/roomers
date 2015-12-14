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

	public List<Bookings> getBookingsOfDate(String date){
		
		/*db.mycollection.find({ "dt" : { "$gte" : { "$date" : "2013-10-01T00:00:00.000Z"}}})*/
		/*db.bookings.find({"startDate":{"$gte":ISODate("2015-10-15T00:00:00.00Z")}});*/
		System.out.println("***************"+date);
		BasicQuery basicQuery= new BasicQuery("{ \"startDate\" : { \"$gte\" : ISODate(\""+date+"\")}}");
		List<BookingsCollection> allBookings = mongoOperations.find(basicQuery,BookingsCollection.class);
		List<Bookings> requiredBookingList = new ArrayList<Bookings>();
		for(BookingsCollection bookingCollection : allBookings){
			System.out.println(bookingCollection);
			requiredBookingList.add(new Bookings(bookingCollection));
		}
		return requiredBookingList;
	}
	
	
	public List<Bookings> bookingsOfRange(Date fromDate,Date toDate){
		
		
		
		return null;
	}
		
}
