package com.roommanagement.services;

import java.util.List;

import com.roommanagement.beans.Room;
import com.roommanagement.collections.RoomCollection;

public interface RoomService {

	void insert(RoomCollection roomDetails);
	
	Boolean checkRoomNameAvailablility(String roomName);
	
	/***********RoomService.java***************/
	
	
	List<Room> getRooms();
}
