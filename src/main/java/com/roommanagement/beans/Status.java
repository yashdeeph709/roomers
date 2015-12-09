package com.roommanagement.beans;

public class Status {
	
	private String status;
	private String message;
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
	
	public Status(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	public Status(){
		
	}
	
	@Override
	public String toString() {
		return "Status [status=" + status + ", message=" + message + "]";
	}
	
	
}
