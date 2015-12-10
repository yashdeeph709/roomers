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
	public void delete(String name) {
		System.out.println("*********Hello****************"+name);
		roomRepository.deleteByRoomName(name);
	}


	public List<RoomCollection> getRooms() {
		return roomRepository.findAll();
	}
	
	public RoomCollection getRequiredRoom(String id) {
		
		//String ids="566853cfb399e2db4beac18f";
		//BasicQuery basicQuery= new BasicQuery("{ _id : ObjectId(\"566853cfb399e2db4beac18f\")}");
		//System.out.println(mongoOperations.findOne(basicQuery,RoomCollection.class));
		
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
		BasicQuery basicQuery= new BasicQuery("{ roomName : \""+room.getRoomName()+"\" }");
			
			RoomCollection roomTest=mongoOperations.findOne(basicQuery,RoomCollection.class);
			if(roomTest==null)
			{
				
				System.out.println("Room does not exists ");
				
			}
			else
			{
				room.setId(roomTest.getId());
							
				roomRepository.save(room);
				
				System.out.println("Room modified successfully");

			}
		}

	public Status<Long> getRoomCount() {
		
		return new Status<Long>("success","Got Room Count",roomRepository.count());
	}


}
