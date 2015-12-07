package com.roommanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.roommanagement.collections.RoomDetails;

public interface CreateRoomRepository extends MongoRepository<RoomDetails, String> {

}
