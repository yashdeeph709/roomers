package com.roommanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.roommanagement.beans.Room;
import com.roommanagement.collections.RoomDetails;
import com.roommanagement.services.CreateRoomService;


@RestController
public class CreateRoomController {

	@Autowired
	CreateRoomService createRoomService;
	
	@RequestMapping("/hello")
	public String sayHello(@RequestParam(value="name", defaultValue="hivan") String name) {
		System.out.println("Hello");
		return "{ Hello : \"Hi\" }";
	}
	
	@RequestMapping(value="/createRoom", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public String registerUser(@RequestBody Room room) {
		
		System.out.println(room);
		RoomDetails roomDetails = new RoomDetails(room.getRoomName(),room.getRoomCity(),room.getRoomLocation(),room.getRoomBlock(),room.getRoomAddress(),room.getRoomCapacity(),room.getRoomTables(),room.getRoomMachines(),room.getRoomScreen(),room.getRoomBoard(),room.getRoomChart(),room.getRoomProjector(),room.getRoomInternet());
		System.out.println("****************"+roomDetails);
		createRoomService.save(roomDetails);
		
		return "SUCCESS";
	}
	
}
