package com.roommanagement.services;

import com.roommanagement.beans.User;

public interface AuthenticationService {
	User validate(String username, String password);
	boolean checkUser(String authToken,int rights);
}
