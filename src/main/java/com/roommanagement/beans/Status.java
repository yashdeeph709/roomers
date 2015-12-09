package com.roommanagement.beans;

import java.util.List;

public class Status<T> {
	
	private String status;
	private String message;
	private List<T> Data;
	private T dataOne;
	
	public Status(String status, String message, T dataOne) {
		this.status = status;
		this.message = message;
		this.dataOne = dataOne;
	}
	public Status(String status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public Status(String status2, String message2, List<T> rooms) {
		this.status = status;
		this.message = message;
		this.Data = rooms;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<T> getData() {
		return Data;
	}
	public void setData(List<T> data) {
		Data = data;
	}
	public T getDataOne() {
		return dataOne;
	}
	public void setDataOne(T dataOne) {
		this.dataOne = dataOne;
	}
}
