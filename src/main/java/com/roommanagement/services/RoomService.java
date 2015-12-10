package com.roommanagement.services;

import java.util.List;

import com.roommanagement.beans.Room;
import com.roommanagement.collections.RoomCollection;

public interface RoomService {

	RoomCollection insert(RoomCollection roomDetails);
	
	Boolean checkRoomNameAvailablility(String roomName);
	
	/***********RoomService.java***************/
	List<RoomCollection> getRooms();
	
	void updateRoom(RoomCollection room); 
	
}
