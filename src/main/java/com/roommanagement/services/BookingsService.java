package com.roommanagement.services;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.roommanagement.beans.Bookings;


public interface BookingsService {

	Bookings insert(Bookings booking,String roomId);
	List<Bookings> getMyBookings(String authToken);
	List<Bookings> getMyBookingsRange(String authToken, int start, int end);
	HttpStatus getStatus(Bookings bookingReturned);
	HttpStatus getStatus(List<Bookings> bookings);



}
