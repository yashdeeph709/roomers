package com.roommanagement.services;

import java.util.Date;
import java.util.List;
import com.roommanagement.beans.Bookings;

public interface AvailabilityService {

	public List<Bookings> getBookingsOfDate(String date);
	
	
}
