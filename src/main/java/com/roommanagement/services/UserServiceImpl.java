package com.roommanagement.services;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roommanagement.beans.UserBean;
import com.roommanagement.collections.UserCollection;
import com.roommanagement.repository.UsersRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UsersRepository repository;

	public void delete(String id) {
		repository.delete(id);
	}


	public List<com.roommanagement.beans.UserBean> getUsers() {
		List<UserBean> users=new ArrayList<UserBean>();
		List<UserCollection> dbusers=repository.findAll();
		Iterator<com.roommanagement.collections.UserCollection> it=dbusers.iterator();
		while(it.hasNext()){
			UserCollection user=it.next();
			users.add(new com.roommanagement.beans.UserBean(user.getId(),user.getName(),user.getEmail()));
		}
		return users;
	}


	public void save(UserCollection userBean) {
		repository.save(userBean);
	}

}
