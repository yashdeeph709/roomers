package com.roommanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.roommanagement.collections.RoomCollection;

public interface RoomRepository extends MongoRepository<RoomCollection, String> {

}
