package com.roommanagement.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Service;
import com.roommanagement.beans.Room;
import com.roommanagement.collections.RoomCollection;
import com.roommanagement.repository.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private MongoOperations mongoOperations;
	private RoomCollection roomUpdated;

	public Room insert(Room room) {
		
		BasicQuery basicQuery= new BasicQuery("{ roomName : \""+room.getRoomName()+"\",\"roomLocation\": \""+room.getRoomLocation()+"\" }");
		RoomCollection roomCollection=mongoOperations.findOne(basicQuery,RoomCollection.class);
		if(roomCollection==null){
		
			return new Room(roomRepository.insert(new RoomCollection(room)));
		}
		else
			return new Room(null);
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
	
	
	public RoomCollection updateRoom(Room room) {
		
		BasicQuery basicQuery= new BasicQuery("{ \"id\" : \""+room.getId()+"\" }");
		RoomCollection roomCollection=mongoOperations.findOne(basicQuery,RoomCollection.class);
		if(roomCollection!=null)
		{	roomCollection.setId(room.getId());			
			roomCollection.setRoomCapacity(room.getRoomCapacity());
			roomCollection.setRoomTables(room.getRoomTables());
			roomCollection.setRoomMachines(room.getRoomMachines());
			roomCollection.setRoomBoard(room.getRoomBoard());
			roomCollection.setRoomChart(room.getRoomChart());
			roomCollection.setRoomScreen(room.getRoomScreen());
			roomCollection.setRoomProjector(room.getRoomProjector());
			roomCollection.setRoomInternet(room.getRoomInternet());
			System.out.println(roomCollection);
			roomUpdated=(RoomCollection) roomRepository.save(roomCollection);
			return roomUpdated;
		}
		return null;
	}
	
	public void delete(String id) {
		roomRepository.delete(id);
	}


	public List<Room> roomRange(int start, int end) {

		BasicQuery basicQuery= new BasicQuery("{}");
		basicQuery.skip(start);
		basicQuery.limit(end);
		
		List<RoomCollection> roomCollectionList = mongoOperations.find(basicQuery, RoomCollection.class);
		List<Room> roomList = new ArrayList<Room>();
		
		for(RoomCollection roomCollection : roomCollectionList){
			roomList.add(new Room(roomCollection));
		}
		
		return roomList; 
	}
}
