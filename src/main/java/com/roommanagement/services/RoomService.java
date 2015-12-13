package com.roommanagement.services;

import java.util.List;

import com.roommanagement.beans.Room;


public interface RoomService {

	Room insert(Room room);
	
	List<Room> getRooms();
	
	Room getRoom(String id);
	
	void updateRoom(Room room);
	
	void delete(String name);
	
	List<Room> roomRange(int start,int end);
	
}
