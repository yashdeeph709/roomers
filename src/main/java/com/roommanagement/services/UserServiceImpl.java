package com.roommanagement.services;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Service;

import com.roommanagement.beans.UserBean;
import com.roommanagement.collections.UserCollection;
import com.roommanagement.repository.UsersRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UsersRepository repository;
	@Autowired
	private MongoOperations mongoOperations;

	public void delete(String id) {
		repository.delete(id);
	}


	public List<com.roommanagement.beans.UserBean> getUsers() {
		List<UserBean> users=new ArrayList<UserBean>();
		List<UserCollection> dbusers=repository.findAll();
		Iterator<com.roommanagement.collections.UserCollection> it=dbusers.iterator();
		while(it.hasNext()){
			UserCollection user=it.next();
			System.out.println(user);
			users.add(new com.roommanagement.beans.UserBean(user.getId(),user.getName(),user.getEmail()));
		}
		return users;
	}


	public UserCollection insert(UserCollection user) {
	BasicQuery basicQuery= new BasicQuery("{ name : \""+user.getEmail()+"\" }");
		
		UserCollection userTest=mongoOperations.findOne(basicQuery,UserCollection.class);
		
	
		if(userTest!=null)
		{
			
			System.out.println("User already exists "+userTest.getName());
			
		}
		else
		{
			return repository.insert(user);
		}
		return user;
		
		
	}

}
