package com.roommanagement.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.roommanagement.beans.Room;
import com.roommanagement.beans.Status;
import com.roommanagement.collections.RoomCollection;
import com.roommanagement.services.RoomService;
import com.roommanagement.services.UserService;


@CrossOrigin
@RestController
@RequestMapping("/RoomManagement")
public class RoomController{

	@Autowired
	RoomService roomservice;
	@Autowired
	UserService service;
	
	
	@RequestMapping(value="/createRoom/{id}", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public Status<RoomCollection> registerRoom(@RequestBody Room room,@PathVariable String id) {
		if(service.checkUser(id)){
			return new Status<RoomCollection>("NotAuthenticated","User not Authenticated");
		}
		RoomCollection roomDetails = new RoomCollection(room.getRoomName(),room.getRoomCity(),room.getRoomLocation(),room.getRoomBlock(),room.getRoomAddress(),room.getRoomCapacity(),room.getRoomTables(),room.getRoomMachines(),room.getRoomScreen(),room.getRoomBoard(),room.getRoomChart(),room.getRoomProjector(),room.getRoomInternet());
			RoomCollection roomCollectionReturned = roomservice.insert(roomDetails);
			
		return  new Status<RoomCollection>("true",roomCollectionReturned.getRoomName());
	}
	
	@RequestMapping("/getRooms/{id}")
	public Status<RoomCollection> getRooms(@PathVariable String id) {
		if(service.checkUser(id)){
			
			return new Status<RoomCollection>("NotAuthenticated","User not Authenticated");
		}
		return new Status<RoomCollection>("success","successfull",roomservice.getRooms());
	}
	
	@RequestMapping(value="/updateRoom/{id}", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public Status<RoomCollection> updateRoom(@RequestBody Room room,@PathVariable String id) {
		if(service.checkUser(id)){
			
			return new Status<RoomCollection>("NotAuthenticated","User not Authenticated");
		}
			RoomCollection roomDetails = new RoomCollection(room.getRoomName(),room.getRoomCity(),room.getRoomLocation(),room.getRoomBlock(),room.getRoomAddress(),room.getRoomCapacity(),room.getRoomTables(),room.getRoomMachines(),room.getRoomScreen(),room.getRoomBoard(),room.getRoomChart(),room.getRoomProjector(),room.getRoomInternet());
			roomservice.updateRoom(roomDetails);
			return  new Status<RoomCollection>("true",roomDetails.getRoomName());
	}
	
	@RequestMapping(value="/availRoomName/{roomName}/{id}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public Status<RoomCollection> checkRoom(@PathVariable("roomName") String roomName,@PathVariable String id) {
		if(service.checkUser(id)){
			return new Status<RoomCollection>("NotAuthenticated","User not Authenticated");
		}
			Boolean available = roomservice.checkRoomNameAvailablility(roomName);
		if(available)
			return  new Status<RoomCollection>("true","Room of this name is not there");
		else
			return  new Status<RoomCollection>("false","Room of this name is already there");
	}
}
