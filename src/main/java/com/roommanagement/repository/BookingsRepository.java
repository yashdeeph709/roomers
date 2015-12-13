package com.roommanagement.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.roommanagement.collections.RoomCollection;

public interface BookingsRepository extends MongoRepository<RoomCollection, String> {

}
