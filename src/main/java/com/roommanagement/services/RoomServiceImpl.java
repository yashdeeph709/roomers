package com.roommanagement.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	private Room roomUpdated;
	HttpStatus httpStatus;
	HttpHeaders httpHeaders=new HttpHeaders();

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
	
	
	public Room updateRoom(Room room) {
		
		BasicQuery basicQuery= new BasicQuery("{ \"_id\" : \""+room.getId()+"\" }");
		RoomCollection roomCollection=mongoOperations.findOne(basicQuery,RoomCollection.class);
		if(roomCollection!=null)
		{	roomCollection.setId(room.getId());
			if(room.getRoomName() != null) {
				roomCollection.setRoomName(room.getRoomName());
			}
			if(room.getRoomLocation() != null) {
				roomCollection.setRoomLocation(room.getRoomLocation());
			}
			if(room.getRoomCity() != null) {
				roomCollection.setRoomCity(room.getRoomCity());
			}
			if(room.getRoomAddress() != null) {
				roomCollection.setRoomAddress(room.getRoomAddress());
			}
			if(room.getRoomBlock() != null) {
				roomCollection.setRoomBlock(room.getRoomBlock());
			}
			roomCollection.setRoomCapacity(room.getRoomCapacity());
			roomCollection.setRoomTables(room.getRoomTables());
			roomCollection.setRoomMachines(room.getRoomMachines());
			roomCollection.setRoomBoard(room.getRoomBoard());
			roomCollection.setRoomChart(room.getRoomChart());
			roomCollection.setRoomScreen(room.getRoomScreen());
			roomCollection.setRoomProjector(room.getRoomProjector());
			roomCollection.setRoomInternet(room.getRoomInternet());
			System.out.println(roomCollection);
			roomUpdated=new Room(roomRepository.save(roomCollection));
			return roomUpdated;
		}
		return null;
	}
	public void delete(String name) {
	
		roomRepository.delete(name);
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
	
	//************getStatus*****************
	public ResponseEntity<Room> getStatus(Room roomReturned)
	{
		if(roomReturned == null){
			return new ResponseEntity<Room>(roomReturned, httpHeaders,HttpStatus.BAD_REQUEST);
			//return HttpStatus.NOT_FOUND;
		}else{
			return new ResponseEntity<Room>(roomReturned, httpHeaders,HttpStatus.ACCEPTED);				//If room is created
		
	}
	}
		
	public ResponseEntity<Map<String,String>> getStatus(Room roomUpdated,HttpHeaders httpHeaders)
	{		HashMap<String,String> response=new HashMap<String, String>();
			HttpStatus status=null;
			if(roomUpdated == null){
				response.put("message","Room Does not Exists");
				status=HttpStatus.BAD_REQUEST;
			}else{
				response.put("message","Room Updated Successfully");
				status=HttpStatus.ACCEPTED;
			}
			return new ResponseEntity<Map<String,String>>(response, httpHeaders,status);			
	}
	public ResponseEntity<List<Room>> getStatus(List<Room> roomList)
	{
		if(roomList==null){
			return new ResponseEntity<List<Room>>(roomList, httpHeaders,HttpStatus.NO_CONTENT);//If no rooms are there in the db 
		}
		else{
			return new ResponseEntity<List<Room>>(roomList, httpHeaders,HttpStatus.ACCEPTED);			//If room are there
		}
		
	}



	
}
