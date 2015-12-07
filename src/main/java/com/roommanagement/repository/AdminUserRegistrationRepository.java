package com.roommanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.roommanagement.beans.UserBean;
import com.roommanagement.collections.AdminUserRegistration;


public interface AdminUserRegistrationRepository extends MongoRepository<UserBean, String> {
	}



