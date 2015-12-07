package com.roommanagement.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="user_registrations")
public class AdminUserRegistration {

	@Id
	private String id;
	private String userName;
	private String firstName;
	private String password;

	public AdminUserRegistration(String userName, String firstName, String password) {
		this.userName = userName;
		this.firstName = firstName;;
		this.password = password;
	}
	
	public String getId() {
		return id;
	}

	public AdminUserRegistration() {
		
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String toString() {
		return firstName + "\t" + userName + "\t" + password;
	}
}



