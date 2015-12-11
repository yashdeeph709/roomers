package com.roommanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Service;

import com.roommanagement.beans.Status;
import com.roommanagement.collections.RoomCollection;
import com.roommanagement.collections.UserCollection;
import com.roommanagement.repository.RoomRepository;



@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private MongoOperations mongoOperations;

	public RoomCollection insert(RoomCollection roomDetails) {
		
		return roomRepository.insert(roomDetails);	
	}

	/*******************RoomServiceImpl.java*******************/
	public void delete(String id) {
		roomRepository.deleteById(id);
	}


	public List<RoomCollection> getRooms() {
		return roomRepository.findAll();
	}
	
	public RoomCollection getRequiredRoom(String id) {
		RoomCollection room=roomRepository.findOne(id);
		return room;
	}

	public Boolean checkRoomNameAvailablility(String roomName) {
			List<RoomCollection> roomCollection = roomRepository.findByRoomName(roomName);
			
			if(roomCollection.size()!=0)
				return false;
			else
				return true;
	}

	/***********************Update Room************************/
	public void updateRoom(RoomCollection room) {
		BasicQuery basicQuery= new BasicQuery("{ roomName : \""+room.getRoomName()+"\",roomBlock : \""+room.getRoomBlock()+"\",roomLocation:\""+room.getRoomLocation()+"\" }");
			RoomCollection roomTest=mongoOperations.findOne(basicQuery,RoomCollection.class);
			if(roomTest!=null)
			{				
				room.setId(roomTest.getId());
				roomRepository.save(room);
			}
		}

	public Status<Long> getRoomCount() {
		
		return new Status<Long>("success","Got Room Count",roomRepository.count());
	}


}
