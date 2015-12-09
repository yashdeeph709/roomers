package com.roommanagement.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.roommanagement.beans.Room;
import com.roommanagement.collections.RoomCollection;
import com.roommanagement.repository.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepository roomRepository;

	public RoomCollection insert(RoomCollection roomDetails) {
		
		return roomRepository.insert(roomDetails);	
	}

	/*******************RoomServiceImpl.java*******************/
	

	public List<Room> getRooms() {
		List<Room> rooms=new ArrayList<Room>();
		List<RoomCollection> dbrooms=roomRepository.findAll();
		Iterator<RoomCollection> roomIterator=dbrooms.iterator();
		while(roomIterator.hasNext()){
			RoomCollection room=roomIterator.next();
			rooms.add(new Room(room.getRoomName(),room.getRoomCity(),room.getRoomLocation(),room.getRoomBlock(),room.getRoomAddress(),room.getRoomCapacity(),room.getRoomTables(),room.getRoomMachines(),room.getRoomBoard(),room.getRoomChart(),room.getRoomScreen(),room.getRoomProjector(),room.getRoomInternet()));
		}
		return rooms;
	}

	public Boolean checkRoomNameAvailablility(String roomName) {
			List<RoomCollection> roomCollection = roomRepository.findByRoomName(roomName);
			
			if(roomCollection.size()!=0)
				return false;
			else
				return true;
	}
}
