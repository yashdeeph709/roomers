package com.roommanagement.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Service;

import com.roommanagement.beans.Bookings;
import com.roommanagement.beans.Room;
import com.roommanagement.collections.BookingsCollection;
import com.roommanagement.collections.RoomCollection;
import com.roommanagement.repository.BookingsRepository;
import com.roommanagement.repository.RoomRepository;

@Service
public class BookingsServiceImpl implements BookingsService {

	@Autowired
	private BookingsRepository bookingsRepository;
	
	@Autowired
	private MongoOperations mongoOperations;

	public Bookings insert(Bookings booking) {

		return new Bookings(bookingsRepository.insert(new BookingsCollection(booking)));
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



}
