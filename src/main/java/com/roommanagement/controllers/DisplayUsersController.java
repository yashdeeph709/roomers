package com.roommanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.roommanagement.beans.UserBean;
import com.roommanagement.repository.UsersRepository;

@RestController
public class DisplayUsersController {

	@Autowired
	private UsersRepository repository;
	
	@RequestMapping("/getUsers")
	public List<UserBean> getUsers() {
		return repository.findAll();
	}
}
