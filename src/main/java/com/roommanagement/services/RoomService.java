package com.roommanagement.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
	ResponseEntity<Room> getStatus(Room roomReturned);
	ResponseEntity<List<Room>> getStatus(List<Room> roomList);
	ResponseEntity<Map<String,String>> getStatus(Room updateRoom, HttpHeaders httpHeaders);
	
}
