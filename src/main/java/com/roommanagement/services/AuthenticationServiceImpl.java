package com.roommanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Service;

import com.roommanagement.beans.User;
import com.roommanagement.collections.UserCollection;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired
	MongoOperations mongoOperations;
	
	/*
	 * (non-Javadoc)
	 * @see com.roommanagement.services.AuthenticationService#validate(java.lang.String, java.lang.String)
	 * This method will take a email and password for a user and will return either a user object or null if user not exists.
	 */
	
	public User validate(String email, String password) {
		BasicQuery basicQuery= new BasicQuery("{ \"email\" : \""+email+"\",\"password\":\""+password+"\" }");
		UserCollection userCollection=mongoOperations.findOne(basicQuery,UserCollection.class);
		if(userCollection==null){
			return null;
		}
		User user=new User(userCollection);
		return user;
	}

	public boolean checkUser(String authToken,int rights) {
		BasicQuery basicQuery= new BasicQuery("{ \"id\" : \""+authToken+"\",rights:"+rights+" }");
		UserCollection userCollection=mongoOperations.findOne(basicQuery,UserCollection.class);
		if(userCollection!=null){
			return false;
		}
		return true;
	}
}
