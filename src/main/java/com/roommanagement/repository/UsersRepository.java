package com.roommanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.roommanagement.beans.UserBean;

public interface UsersRepository extends MongoRepository<UserBean, String> {
	
}
