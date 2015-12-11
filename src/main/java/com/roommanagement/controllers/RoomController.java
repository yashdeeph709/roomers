package com.roommanagement.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	
	@RequestMapping(value="/createRoom", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public Status<RoomCollection> registerRoom(@RequestBody Room room,@RequestHeader String authToken) {
		if(service.checkAdmin(authToken)){
			return new Status<RoomCollection>("NotAuthenticated","User not Authenticated");
		}
		RoomCollection roomDetails = new RoomCollection(room.getRoomName(),room.getRoomCity(),room.getRoomLocation(),room.getRoomBlock(),room.getRoomAddress(),room.getRoomCapacity(),room.getRoomTables(),room.getRoomMachines(),room.getRoomScreen(),room.getRoomBoard(),room.getRoomChart(),room.getRoomProjector(),room.getRoomInternet());
			RoomCollection roomCollectionReturned = roomservice.insert(roomDetails);
			
		return  new Status<RoomCollection>("true",roomCollectionReturned.getRoomName());
	}
	
	@RequestMapping("/getRooms")
	public Status<RoomCollection> getRooms(@RequestHeader String authToken) {
		if(service.checkAdmin(authToken)){
			
			return new Status<RoomCollection>("NotAuthenticated","User not Authenticated");
		}
		return new Status<RoomCollection>("success","successfull",roomservice.getRooms());
	}
	
	/**********************Get Required Room******************/
	@RequestMapping(value="/getRequiredRoom/{id}")//, method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public Status<RoomCollection> getRequiredRoom(@RequestHeader String authToken,@PathVariable("id") String id) 
	{
		if(service.checkAdmin(authToken)){
			
			return new Status<RoomCollection>("NotAuthenticated","User not Authenticated");
		}
		return new Status<RoomCollection>("success","successfull",roomservice.getRequiredRoom(id));
		//return roomservice.getRequiredRoom("shruti");
	}
	/***********************Update Room************************/
	@RequestMapping(value="/updateRoom", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public Status<RoomCollection> updateRoom(@RequestBody Room room,@RequestHeader String authToken) {
		if(service.checkAdmin(authToken)){
			return new Status<RoomCollection>("NotAuthenticated","User not Authenticated");
		}
			RoomCollection roomDetails = new RoomCollection(room.getRoomName(),room.getRoomCity(),room.getRoomLocation(),room.getRoomBlock(),room.getRoomAddress(),room.getRoomCapacity(),room.getRoomTables(),room.getRoomMachines(),room.getRoomScreen(),room.getRoomBoard(),room.getRoomChart(),room.getRoomProjector(),room.getRoomInternet());
			roomservice.updateRoom(roomDetails);
			return  new Status<RoomCollection>("true",roomDetails.getRoomName());
	}
					
	/***********DeleteRoom Controller************/
	
	@RequestMapping(value="/deleteRoom/{id}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status deletRoom(@PathVariable("id") String id) {
		roomservice.delete(id);
		return new Status<RoomCollection>("true","Room Deleted Successfully!");
	}
	
	
	@RequestMapping(value="/availRoomName/{roomName}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public Status<RoomCollection> checkRoom(@PathVariable("roomName") String roomName,@RequestHeader String authToken) {
		if(service.checkAdmin(authToken)){
			return new Status<RoomCollection>("NotAuthenticated","User not Authenticated");
		}
		Boolean available = roomservice.checkRoomNameAvailablility(roomName);
		if(available)
			return  new Status<RoomCollection>("true","Room of this name is not there");
		else
			return  new Status<RoomCollection>("false","Room of this name is already there");
	}
	
	@RequestMapping("/getRoomsCount")
	public Status getRoomsCount() {
		
		return roomservice.getRoomCount();
	}
}
