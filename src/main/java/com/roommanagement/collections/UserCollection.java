package com.roommanagement.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.roommanagement.beans.User;

@Document(collection="users")
public class UserCollection {
	@Id
	private String id;
	private String name;
	private String email;
	private String password;
	private int rights;
	
	public UserCollection(String name, String email, String password,
			int rights) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.rights = rights;
	}
	public UserCollection(User user) {
		super();
		this.name = user.getName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.rights = user.getRights();
	}
	public int getRights() {
		return rights;
	}
	public void setRights(int rights) {
		this.rights = rights;
	}
	public UserCollection() {
		super();
		
	}
	@Override
	public String toString() {
		return "UserCollection [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", rights=" + rights + "]";
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
