package com.roommanagement.services;

import java.util.List;

import com.roommanagement.beans.Status;
import com.roommanagement.collections.UserCollection;


public interface UserService {
	
	void delete( String id ) ;
	
	List<UserCollection> getUsers();
	
	UserCollection insert(UserCollection userBean);
	
	boolean checkUser(String id);
	
	boolean checkAdmin(String id);
	
	boolean checkSubAdmin(String id);
	
	String getUser();
	
	UserCollection getUser(String email);
	
	String getAdmin();
	
	String getSubAdmin();
	
	boolean validate(String username, String password);
	
	Status<Long> getUsersCount();
}
