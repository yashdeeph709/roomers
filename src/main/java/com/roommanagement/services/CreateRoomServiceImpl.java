package com.roommanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.roommanagement.collections.RoomDetails;
import com.roommanagement.repository.CreateRoomRepository;

@Service
public class CreateRoomServiceImpl implements CreateRoomService {

	@Autowired
	private CreateRoomRepository createRoomRepository;

	public void save(RoomDetails roomDetails) {
		createRoomRepository.save(roomDetails);	
	}
}
