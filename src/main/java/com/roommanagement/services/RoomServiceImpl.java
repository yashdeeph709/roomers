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
	

	public List<RoomCollection> getRooms() {
		return roomRepository.findAll();
	}

	public Boolean checkRoomNameAvailablility(String roomName) {
			List<RoomCollection> roomCollection = roomRepository.findByRoomName(roomName);
			
			if(roomCollection.size()!=0)
				return false;
			else
				return true;
	}
}
