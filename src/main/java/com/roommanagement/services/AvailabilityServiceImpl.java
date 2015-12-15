package com.roommanagement.services;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.roommanagement.beans.Bookings;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {
	
	@Autowired
	private MongoOperations mongoOperations;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	Date formattedDate;
	
	

	public List<Bookings> getBookingsOfDate(Date date) throws UnknownHostException{
		
		Bookings bookings = null;
		List<Bookings> requiredBookingList = new ArrayList<Bookings>();
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("test");
		DBCollection collection = db.getCollection("bookings");
		
		BasicDBObject andQuery = new BasicDBObject();
		andQuery.put("startDate", new BasicDBObject("$lte", date));
		andQuery.put("endDate", new BasicDBObject("$gte", date));

		DBCursor cursor = collection.find(andQuery);
		while (cursor.hasNext()) {
			
			DBObject dbObj = cursor.next();
			bookings = mongoOperations.getConverter().read(Bookings.class, dbObj);
			requiredBookingList.add(bookings);
		}
		
		return requiredBookingList;
	}
	
	
	public Map<Integer,List<Bookings>> bookingsOfRange(Date fromDate, Date toDate) throws ParseException, UnknownHostException{
		
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
