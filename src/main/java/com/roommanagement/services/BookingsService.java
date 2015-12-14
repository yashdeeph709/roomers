package com.roommanagement.services;

import java.util.List;

import com.roommanagement.beans.Bookings;


public interface BookingsService {

	Bookings insert(Bookings booking);
	List<Bookings> getMyBookings(String authToken);
	List<Bookings> getMyBookingsRange(String authToken, int start, int end);



}
