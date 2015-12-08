package com.roommanagement.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="users")
public class UserCollection {
	@Id
	private String id;
	private String name;
	private String email;
	private String password;
	private String rights;
	
	public UserCollection(String name, String email, String password,
			String rights) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.rights = rights;
	}
	public String getRights() {
		return rights;
	}
	public void setRights(String rights) {
		this.rights = rights;
	}
	public UserCollection() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserBean [Id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
