package com.roommanagement.services;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.roommanagement.beans.Room;
import com.roommanagement.collections.RoomCollection;


public interface RoomService {

	Room insert(Room room);
	
	List<Room> getRooms();
	
	Room getRoom(String id);
	
	Room updateRoom(Room room);
	
	void delete(String name);
	
	List<Room> roomRange(int start,int end);
	
	//***************GetStatus***********
	HttpStatus getStatus(Room roomReturned);
	HttpStatus getStatus(List<Room> roomList);
	
}
