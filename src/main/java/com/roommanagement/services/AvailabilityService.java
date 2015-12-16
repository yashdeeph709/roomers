package com.roommanagement.services;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.roommanagement.beans.Bookings;

public interface AvailabilityService {

	public List<Bookings> getBookingsForDate(Date date)throws UnknownHostException;
	
	public Map<Integer,List<Bookings>> getBookingsForDates(Date fromDate)throws ParseException, UnknownHostException;
}
