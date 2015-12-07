package com.roommanagement.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roommanagement.repository.AdminUserRegistrationRepository;

@Service
public class AdminUserRegistrationServiceImpl implements AdminUserRegistrationService{

	@Autowired
	private AdminUserRegistrationRepository adminUserRegistrationRepository;
	

	public void delete(String id) {
		adminUserRegistrationRepository.delete(id);
	}

}
