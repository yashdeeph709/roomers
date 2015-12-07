package com.roommanagement.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="users")
public class UserBean {
	@Id
	private String Id;
	private String name;
	private String email;
	private String password;
	public UserBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserBean(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserBean [Id=" + Id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
