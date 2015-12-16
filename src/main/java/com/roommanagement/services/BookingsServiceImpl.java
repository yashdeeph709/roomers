package com.roommanagement.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.roommanagement.beans.Bookings;
import com.roommanagement.beans.Room;
import com.roommanagement.collections.BookingsCollection;
import com.roommanagement.collections.RoomCollection;
import com.roommanagement.repository.BookingsRepository;

@Service
public class BookingsServiceImpl implements BookingsService {

	@Autowired
	private BookingsRepository bookingsRepository;
	
	@Autowired
	private MongoOperations mongoOperations;
	private BasicQuery basicQuery;
	private Bookings allocatedRoom;
	private HttpHeaders httpHeaders = new HttpHeaders();

	public Bookings bookRoom(Bookings booking,String roomId) {
		if(roomId==null){
			
			return null;			//If Bookings is not inserted
		}
		else{
		
		basicQuery= new BasicQuery("{ \"id\" : \""+roomId+"\" }");
		RoomCollection roomCollection=mongoOperations.findOne(basicQuery,RoomCollection.class);
		Room room=new Room(roomCollection);
		booking.setRoom(room);
		
		return new Bookings(bookingsRepository.insert(new BookingsCollection(booking)));
		}
	}
	

	
	public List<Bookings> getMyBookings(String authToken) {
		BasicQuery basicQuery = new BasicQuery("{ requestee : \"" + authToken + "\"}");
		List<BookingsCollection> bookingsCollectionList = mongoOperations.find(basicQuery, BookingsCollection.class);
		List<Bookings> requiredBookings = new ArrayList<Bookings>();
		for (BookingsCollection bookingCollection : bookingsCollectionList) {
			requiredBookings.add(new Bookings(bookingCollection));
		}
		return requiredBookings;
	}

	public List<Bookings> getMyBookingsRange(String authToken, int start, int end) {

		BasicQuery basicQuery = new BasicQuery("{ requestee : \"" + authToken +" \"}");
		basicQuery.skip(start);
		basicQuery.limit(end);
		List<BookingsCollection> bookingsCollectionList = mongoOperations.find(basicQuery, BookingsCollection.class);
		List<Bookings> requiredBookings = new ArrayList<Bookings>();
		for (BookingsCollection bookingCollection : bookingsCollectionList) {
			requiredBookings.add(new Bookings(bookingCollection));
		}
		return requiredBookings;
	}

	public Bookings allocateRoom(Bookings requestedBooking){
		BasicQuery basicQuery= new BasicQuery("{ \"id\" : \""+requestedBooking.getId()+"\" }");
		BookingsCollection booking=mongoOperations.findOne(basicQuery,BookingsCollection.class);
		if(booking!=null)
		{	
			booking.setId(requestedBooking.getId()); 	
			booking.setStatus(requestedBooking.getStatus());
			allocatedRoom=new Bookings(bookingsRepository.save(booking));
			return allocatedRoom;
		}
		return null;

	}
	
	/************Room Cancellation**************/
	public void cancel(String name) {
		bookingsRepository.delete(name);
	}

	
	/************getStatus****************/

	public ResponseEntity<Bookings> getStatus(Bookings bookingReturned)
	{
		if(bookingReturned == null){
			return new ResponseEntity<Bookings>(bookingReturned, httpHeaders,HttpStatus.BAD_REQUEST);
		}
		else{
			return new ResponseEntity<Bookings>(bookingReturned, httpHeaders,HttpStatus.CREATED);					//If Bookings is created
		}
		
	}
	
	public ResponseEntity<List<Bookings>> getStatus(List<Bookings> bookings)
	{
		if(bookings==null){
			return new ResponseEntity<List<Bookings>>(bookings, httpHeaders, HttpStatus.NO_CONTENT); 				//If no rooms are there in the db 
		}
		else{
			return new ResponseEntity<List<Bookings>>(bookings, httpHeaders, HttpStatus.ACCEPTED);				//If room are there
		}
		
	}




	public ResponseEntity<String> getStatus(Bookings allocateRoom, HttpHeaders httpHeaders) {
		
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}
	



}
