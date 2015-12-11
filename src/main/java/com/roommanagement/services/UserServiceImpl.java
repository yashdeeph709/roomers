package com.roommanagement.services;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;

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
	BasicQuery basicQuery= new BasicQuery("{ email : \""+user.getEmail()+"\" }");
		UserCollection userTest=mongoOperations.findOne(basicQuery,UserCollection.class);
		if(userTest==null)
		{
			user.setRights("2.0");
			return repository.insert(user);
		}
		return null;
	}


	public boolean checkAdmin(String id) {
		UserCollection user=repository.findOne(id);
		System.out.println("checkAdmin");
		System.out.println(user);
		System.out.println(user);
		System.out.println("INside getAdmin");
		if(user==null){
			return true;
		}
		System.out.println(user.getRights());		
		if(user.getRights().equals("0.0")){
			return false;
		}
		return true;
	}
	public boolean checkSubAdmin(String id) {
		UserCollection user=repository.findOne(id);
		System.out.println("INside getsub");
		if(user==null){
			return true;
		}
		if(user.getRights().equals("1.0")){
			return false;
		}
		return true;
	}

	public boolean checkUser(String id) {
		UserCollection user=repository.findOne(id);
		System.out.println("INside getuser");
		if(user==null){
			return true;
		}
		if(user.getRights().equals("2.0")){
			return false;
		}
		return true;
	}
	public boolean validate(String username, String password) {
				BasicQuery basicQuery= new BasicQuery("{ \"email\" : \""+username+"\",\"password\":\""+password+"\" }");
				System.out.println("{ \"email\" : \""+username+"\",\"password\":\""+password+"\" }");
				UserCollection userTest=mongoOperations.findOne(basicQuery,UserCollection.class);
				System.out.println(userTest);
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
		System.out.println(userTest);
		System.out.println("INside getAdmin");
		return userTest.getId();
	}


	public String getSubAdmin() {
		BasicQuery basicQuery= new BasicQuery("{ rights : 1 }");
		UserCollection userTest=mongoOperations.findOne(basicQuery,UserCollection.class);
		return userTest.getId();
	}


	public UserCollection getUser(String id) {
				BasicQuery basicQuery= new BasicQuery("{ email : \""+id+"\" }");
				UserCollection userTest=mongoOperations.findOne(basicQuery,UserCollection.class);
				return userTest;
			}
}
