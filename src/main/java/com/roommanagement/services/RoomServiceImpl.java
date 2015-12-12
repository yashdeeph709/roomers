package com.roommanagement.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Service;

import com.roommanagement.beans.Room;
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

	public Room insert(Room room) {
		
		RoomCollection roomCollection = new RoomCollection(room);
		
		return new Room(roomRepository.insert(roomCollection));	
	}

	
	public List<Room> getRooms() {
		List<RoomCollection> roomCollectionList = roomRepository.findAll();
		List<Room> roomList = new ArrayList<Room>();
		
		for(RoomCollection roomCollection : roomCollectionList){
			roomList.add(new Room(roomCollection));
		}
		
		return roomList;
	}
	
	public Room getRoom(String id) {
		
		RoomCollection roomCollection=roomRepository.findOne(id);
		
		return new Room(roomCollection);
	}
	
	
	public void updateRoom(Room room) {
		BasicQuery basicQuery= new BasicQuery("{ roomName : \""+room.getRoomName()+"\",roomBlock : \""+room.getRoomBlock()+"\",roomLocation:\""+room.getRoomLocation()+"\" }");
		RoomCollection roomTest=mongoOperations.findOne(basicQuery,RoomCollection.class);
		if(roomTest!=null)
		{				
			roomRepository.save(roomTest);
		}
	}
	
	public void delete(String id) {
		roomRepository.deleteById(id);
	}


	
	
	

	public Boolean checkRoomNameAvailablility(String roomName) {
			List<RoomCollection> roomCollection = roomRepository.findByRoomName(roomName);
			
			if(roomCollection.size()!=0)
				return false;
			else
				return true;
	}

	

	public Status<Long> getRoomCount() {
		
		return new Status<Long>("success","Got Room Count",roomRepository.count());
	}


}
