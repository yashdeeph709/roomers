package com.roommanagement.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Service;

import com.roommanagement.beans.Status;
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
	BasicQuery basicQuery= new BasicQuery("{ email : \""+user.getEmail()+"\" }");
		UserCollection userTest=mongoOperations.findOne(basicQuery,UserCollection.class);
		if(userTest==null)
		{
			user.setRights(2);
			return repository.insert(user);
		}
		return null;
	}


	public boolean checkAdmin(String id) {
		UserCollection user=repository.findOne(id);
		if(user==null){
			return true;
		}
		if(user.getRights()==0){
			return true;
		}
		return true;
	}
	public boolean checkSubAdmin(String id) {
		UserCollection user=repository.findOne(id);
		System.out.println("INside getsub");
		if(user==null){
			return true;
		}
		if(user.getRights()==1){
			return false;
		}
		return true;
	}

	public boolean checkUser(String id) {
		UserCollection user=repository.findOne(id);
		if(user==null){
			return true;
		}
		if(user.getRights()==2){
			return false;
		}
		return true;
	}
	public boolean validate(String username, String password) {
				BasicQuery basicQuery= new BasicQuery("{ \"email\" : \""+username+"\",\"password\":\""+password+"\" }");
				UserCollection userTest=mongoOperations.findOne(basicQuery,UserCollection.class);
				if(userTest==null){
					return false;
				}
				return true;
			}

	public String getUser() {
		BasicQuery basicQuery= new BasicQuery("{ rights : 2 }");
		UserCollection userTest=mongoOperations.findOne(basicQuery,UserCollection.class);
		return userTest.getId();
	}


	public String getAdmin() {
		BasicQuery basicQuery= new BasicQuery("{ rights : 0 }");
		UserCollection userTest=mongoOperations.findOne(basicQuery,UserCollection.class);
		return userTest.getId();
	}


	public String getSubAdmin() {
		BasicQuery basicQuery= new BasicQuery("{ rights : 1 }");
		UserCollection userTest=mongoOperations.findOne(basicQuery,UserCollection.class);
		return userTest.getId();
	}


	public UserCollection getUser(String email) {
				BasicQuery basicQuery= new BasicQuery("{ email : \""+email+"\" }");
				UserCollection userTest=mongoOperations.findOne(basicQuery,UserCollection.class);
				return userTest;
			}


	public Status<Long> getUsersCount() {
		return new Status<Long>("success","Got Users Count",repository.count());
	}
}
