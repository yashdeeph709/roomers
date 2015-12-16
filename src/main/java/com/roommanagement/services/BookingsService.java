package com.roommanagement.services;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.roommanagement.beans.Bookings;
import com.roommanagement.beans.Room;


public interface BookingsService {

	Bookings insert(Bookings booking,String roomId);
	List<Bookings> getMyBookings(String authToken);
	List<Bookings> getMyBookingsRange(String authToken, int start, int end);
	ResponseEntity<Bookings> getStatus(Bookings bookingReturned);
	ResponseEntity<List<Bookings>> getStatus(List<Bookings> bookings);
	Bookings allocateRoom(Bookings requestedBooking);
	ResponseEntity<String> getStatus(Bookings allocateRoom, HttpHeaders httpHeaders);
	void cancel(String name);



}
