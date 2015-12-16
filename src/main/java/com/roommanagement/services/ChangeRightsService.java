package com.roommanagement.services;

import org.springframework.http.ResponseEntity;

import com.roommanagement.beans.User;

public interface ChangeRightsService {

	ResponseEntity<User> updateRightsToSubAdmin(String id);

	ResponseEntity<User> updateRightsToUser(String id);
}
