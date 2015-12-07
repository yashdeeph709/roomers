package com.roommanagement.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateRoomController {

	@RequestMapping("/hello")
	public String sayHello(@RequestParam(value="name", defaultValue="hivan") String name) {
		return "Hello " + name;
	}
}
