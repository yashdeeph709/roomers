package com.roommanagement.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.roommanagement.collections.UserCollection;
import com.roommanagement.repository.UsersRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UsersRepository repository;

	public void delete(String id) {
		repository.delete(id);
	}


	public List<UserCollection> getUsers() {
		
		return repository.findAll();
	}


	public UserCollection insert(UserCollection userBean) {
		
		return repository.insert(userBean);
	}

}
