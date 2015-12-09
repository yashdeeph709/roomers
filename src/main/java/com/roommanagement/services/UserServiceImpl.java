package com.roommanagement.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Service;
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


	public List<UserCollection> getUsers() {
		return repository.findAll();
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


	public boolean checkUser(String id) {				
		return repository.findOne(id)==null?true:false;
	}

}
