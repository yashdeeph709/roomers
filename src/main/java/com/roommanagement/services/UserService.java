package com.roommanagement.services;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.roommanagement.beans.User;
import com.roommanagement.collections.UserCollection;


public interface UserService {
	
	void delete( String id ) ;
	
	List<UserCollection> getUsers();
	
	User insert(UserCollection userBean);
	
	boolean checkUser(String id);
	
	boolean checkAdmin(String id);
	
	boolean checkSubAdmin(String id);
	
	String getUser();
	
	UserCollection getUser(String email);
	
	String getAdmin();
	
	String getSubAdmin();
	
	boolean validate(String username, String password);
	
	Long getUsersCount();
	ResponseEntity<User> getStatus(User createUserReturnValue);
}
