package com.roommanagement.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.roommanagement.collections.RoomCollection;

public interface RoomRepository extends MongoRepository<RoomCollection, String> {
	
	List<RoomCollection> findByRoomName(String roomName);
	
	void deleteById(String Id);
}
