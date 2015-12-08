package com.roommanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.roommanagement.collections.RoomCollection;
import com.roommanagement.repository.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepository createRoomRepository;

	public void save(RoomCollection roomDetails) {
		createRoomRepository.save(roomDetails);	
	}
}
