package com.roommanagement.services;

import java.util.List;
import com.roommanagement.beans.Bookings;
import com.roommanagement.collections.BookingsCollection;


public interface BookingsService {

	Bookings insert(Bookings booking);
	
	List<Bookings> getRooms();
	
	BookingsCollection getRoom(String id);
	
	void updateRoom(Bookings booking);
	
	//void delete(String name);
	
	List<Bookings> roomRange(int start,int end);
	
}
