package com.roommanagement.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.roommanagement.collections.UserCollection;

public interface UsersRepository extends MongoRepository<UserCollection, String> {
	@Query("{'rights' : {$ne : 0}}")
    public List<UserCollection> findByRightsIsNotAdminQuery();
}
