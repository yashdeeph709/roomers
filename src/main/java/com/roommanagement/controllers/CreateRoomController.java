package com.roommanagement.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class CreateRoomController {

	@RequestMapping("/hello")
	public String sayHello(@RequestParam(value="name", defaultValue="Ivan") String name) {
		return "Hello " + name;
	}
}
