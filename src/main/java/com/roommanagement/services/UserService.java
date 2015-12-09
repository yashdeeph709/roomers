package com.roommanagement.services;

import java.util.List;
import com.roommanagement.collections.UserCollection;


public interface UserService {
	void delete( String id ) ;
	List<UserCollection> getUsers();
	UserCollection insert(UserCollection userBean);
	boolean checkUser(String id);
}
