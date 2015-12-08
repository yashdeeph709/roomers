package com.roommanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.roommanagement.beans.Room;
import com.roommanagement.beans.Status;
import com.roommanagement.collections.RoomCollection;
import com.roommanagement.services.RoomService;


@RestController
public class RoomController {

	@Autowired
	RoomService roomservice;
	
	@RequestMapping(value="/createRoom", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public Status registerUser(@RequestBody Room room) {
		RoomCollection roomDetails = new RoomCollection(room.getRoomName(),room.getRoomCity(),room.getRoomLocation(),room.getRoomBlock(),room.getRoomAddress(),room.getRoomCapacity(),room.getRoomTables(),room.getRoomMachines(),room.getRoomScreen(),room.getRoomBoard(),room.getRoomChart(),room.getRoomProjector(),room.getRoomInternet());
		roomservice.save(roomDetails);
		return new Status("success","room created sucessfully");
	}
}
