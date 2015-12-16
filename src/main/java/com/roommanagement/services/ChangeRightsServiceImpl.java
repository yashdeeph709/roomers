package com.roommanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.roommanagement.beans.User;
import com.roommanagement.collections.UserCollection;
import com.roommanagement.repository.UsersRepository;

@Service
public class ChangeRightsServiceImpl implements ChangeRightsService {

	@Autowired
	UsersRepository usersRepository;
	
	public ResponseEntity<User> updateRightsToSubAdmin(String id) {
		
		UserCollection userCollectionOfSubAdminRight = usersRepository.findByRights(1);
		
		if(userCollectionOfSubAdminRight==null){
			UserCollection userCollection = usersRepository.findOne(id);
			if(userCollection!=null){
				userCollection.setRights(1);
			}
			return new ResponseEntity<User>(new User(usersRepository.save(userCollection)),new HttpHeaders(),HttpStatus.ACCEPTED);
		}
		else{
			return new ResponseEntity<User>(new User(userCollectionOfSubAdminRight),new HttpHeaders(),HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<User> updateRightsToUser(String id) {
		
			UserCollection userCollection = usersRepository.findOne(id);
			userCollection.setRights(2);
			return new ResponseEntity<User>(new User(usersRepository.save(userCollection)),new HttpHeaders(),HttpStatus.ACCEPTED);
		
	}

}
