package com.roommanagement.services;

import java.util.List;

import com.roommanagement.beans.Room;
import com.roommanagement.collections.RoomCollection;


public interface RoomService {

	Room insert(Room room);
	
	List<Room> getRooms();
	
	Room getRoom(String id);
	
	RoomCollection updateRoom(Room room);
	
	void delete(String name);
	
	List<Room> roomRange(int start,int end);
	
}
