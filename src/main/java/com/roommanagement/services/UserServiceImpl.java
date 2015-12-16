package com.roommanagement.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.roommanagement.EmailAPI;
import com.roommanagement.beans.Email;
import com.roommanagement.beans.Room;
import com.roommanagement.beans.User;
import com.roommanagement.collections.UserCollection;
import com.roommanagement.repository.UsersRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UsersRepository repository;
	@Autowired
	private MongoOperations mongoOperations;
//	HttpStatus httpstatus=null;
	HttpHeaders responseHeaders;
//	ConfigurableApplicationContext context;//=new ClassPathXmlApplicationContext("Mail-bean.xml");
//	Email sendMail = (Email) context.getBean("mailMail");
	public void delete(String id) {
		
		
		UserCollection user=repository.findOne(id);
	
		ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("Mail-bean.xml");
		Email sendMail = (Email) context.getBean("mailMail");
		sendMail.sendMail("shrutiu.7@gmail.com",
						  user.getEmail(),
						  "User Removal Acknowledgement", 
						  "Dear "+user.getName()+",\n\nYour Room Management account has been deleted by the Admin");
	
		repository.delete(id);
	}


	public List<UserCollection> getUsers() {
		
		return repository.findByRightsIsNotAdminQuery();
		
	}


	public User insert(UserCollection user) {
	BasicQuery basicQuery= new BasicQuery("{ email : \""+user.getEmail()+"\" }");
		User userTest=mongoOperations.findOne(basicQuery,User.class);
		
		ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("Mail-bean.xml");
		Email sendMail = (Email) context.getBean("mailMail");
		
		if(userTest==null)
		{
			user.setRights(2);
			
			sendMail.sendMail("shrutiu.7@gmail.com",
							  user.getEmail(),
							  "Welcome Mail", 
							  "Dear "+user.getName()+",\n\nThis is the Welcome mail from Room Management.You can now login with the following username and password \n\nUsername: "
							  +user.getEmail()+"\nPassword: "+user.getPassword());
	        
			return new User(repository.insert(user));
		}
		return null;
	}


	public boolean checkAdmin(String id) {
		UserCollection user=repository.findOne(id);
		if(user==null){
			return true;
		}
		if(user.getRights()==0){
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


	public Long getUsersCount() {
		return repository.count();
	}
	//***********************Pagination****************
	public List<User> userRange(int start, int end) {

		BasicQuery basicQuery= new BasicQuery("{}");
		basicQuery.skip(start);
		basicQuery.limit(end);

		List<UserCollection> userCollectionList = mongoOperations.find(basicQuery, UserCollection.class);
		List<User> userBeanList = new ArrayList<User>();

		for(UserCollection userCollection : userCollectionList){
			userBeanList.add(new User(userCollection));
		}
		return userBeanList; 
		}
	//*****************get Status*******************
	public ResponseEntity<User> getStatus(User createUserReturnValue)
	{
		if(createUserReturnValue==null)
		{ 
			return new ResponseEntity<User>(createUserReturnValue,responseHeaders,HttpStatus.ALREADY_REPORTED);
			
			
		}
		 return new ResponseEntity<User>(createUserReturnValue,responseHeaders,HttpStatus.CREATED);
	
	}



}
