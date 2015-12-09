package com.roommanagement.services;

import java.util.List;
import com.roommanagement.collections.UserCollection;


public interface UserService {
	void delete( String id ) ;
	List<com.roommanagement.beans.UserBean> getUsers();
	UserCollection insert(UserCollection userBean);
}
