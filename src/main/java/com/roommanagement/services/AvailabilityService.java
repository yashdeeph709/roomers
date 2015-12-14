package com.roommanagement.services;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.roommanagement.beans.Bookings;
import com.roommanagement.beans.User;

public interface AvailabilityService {

	public List<Bookings> getBookingsOfDate(String date);

	public HttpStatus getStatus(List<Bookings> bookingsList);
	
	
}
