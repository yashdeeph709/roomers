package com.roommanagement.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.roommanagement.beans.Bookings;
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
			roomUpdated=new Room(roomRepository.save(roomCollection));
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
	
	//************getStatus*****************
	public ResponseEntity<Room> getStatus(Room roomReturned)
	{
		if(roomReturned == null){
			return new ResponseEntity<Room>(roomReturned, httpHeaders,HttpStatus.NOT_FOUND);
			//return HttpStatus.NOT_FOUND;
		}else{
			return new ResponseEntity<Room>(roomReturned, httpHeaders,HttpStatus.FOUND);				//If room is created
		
	}
	}
		
	public ResponseEntity<String> getStatus(Room roomUpdated,HttpHeaders httpHeaders,HttpStatus httpStatus)
	{
			if(roomUpdated == null){
				return new ResponseEntity<String>("Room Does not Exists", httpHeaders,HttpStatus.NOT_FOUND);
			
			}else{
				return new ResponseEntity<String>("Room details update", httpHeaders,HttpStatus.FOUND);				//If room is updated
			
		}
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
