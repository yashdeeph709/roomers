package com.roommanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.roommanagement.collections.UserCollection;

public interface UsersRepository extends MongoRepository<UserCollection, String> {
	
}
