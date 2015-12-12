package com.roommanagement.beans;

public class User {
	
	private String id;
	private String name;
	private String email;
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
	
	public User(String id, String name, String email, int rights) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.rights = rights;
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
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", rights=" + rights + "]";
	}
	
	
}
