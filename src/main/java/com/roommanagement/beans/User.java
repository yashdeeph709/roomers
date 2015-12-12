package com.roommanagement.beans;

import java.util.List;

import com.roommanagement.collections.UserCollection;

public class User {
	
	private String id;
	private String name;
	private String email;
	private String password;
	private int rights;
	public User() {
		super();
	}
	public User(String id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	
	
	public User(String id, String name, String email, String password, int rights) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.rights = rights;
	}
	public User(UserCollection userCollection) {
		this.id = userCollection.getId();
		this.name = userCollection.getName();
		this.email = userCollection.getEmail();
		this.password = userCollection.getPassword();
		this.rights = userCollection.getRights();
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
	public int getRights() {
		return rights;
	}
	public void setRights(int rights) {
		this.rights = rights;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", rights=" + rights + "]";
	}
	
	
}
