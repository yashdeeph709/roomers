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


	public BookingsCollection getRoom(String id) {
		// TODO Auto-generated method stub
		return null;
	}


	public void updateRoom(Bookings booking) {
		// TODO Auto-generated method stub
		
	}


	public List<Bookings> getRooms() {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Bookings> roomRange(int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}
}
