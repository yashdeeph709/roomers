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


@CrossOrigin
@RestController
@RequestMapping("/RoomManagement")
public class RoomController{

	@Autowired
	RoomService roomservice;
	
	
	@RequestMapping(value="/createRoom", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public Status registerRoom(@RequestBody Room room,@RequestParam String id) {
		RoomCollection roomDetails = new RoomCollection(room.getRoomName(),room.getRoomCity(),room.getRoomLocation(),room.getRoomBlock(),room.getRoomAddress(),room.getRoomCapacity(),room.getRoomTables(),room.getRoomMachines(),room.getRoomScreen(),room.getRoomBoard(),room.getRoomChart(),room.getRoomProjector(),room.getRoomInternet());
			RoomCollection roomCollectionReturned = roomservice.insert(roomDetails);
		return  new Status("true",roomCollectionReturned.getRoomName());
	}
	
	@RequestMapping("/getRooms")
	public List<Room> getRooms(@RequestParam String id) {
		return roomservice.getRooms();
	}
	
	@RequestMapping(value="/availRoomName/{roomName}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public Status checkRoom(@PathVariable("roomName") String roomName,@RequestParam String id) {
		Boolean available = roomservice.checkRoomNameAvailablility(roomName);
		if(available)
			return  new Status("true","Room of this name is not there");
		else
			return  new Status("false","Room of this name is already there");
	}
}
