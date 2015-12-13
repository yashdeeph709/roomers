package com.roommanagement.beans;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.roommanagement.collections.BookingsCollection;
import com.roommanagement.collections.RoomCollection;


public class Bookings {
	
	private String id;
	private Room room;
	private RoomCollection room1;
	@DateTimeFormat(iso=ISO.DATE_TIME)
	private Date startTime;
	@DateTimeFormat(iso=ISO.DATE_TIME)
	private Date endTime;
	@DateTimeFormat(iso=ISO.DATE_TIME)
	private Date startDate;
	@DateTimeFormat(iso=ISO.DATE_TIME)
	private Date endDate;
	private Status status;
	private User requestee;
	private String subject;
	private Category category;
	public Bookings()
	{
		
	}
	
	public Bookings(String id, Room room, Date startTime, Date endTime, Date startDate, Date endDate, Status status,
			User requestee, String subject, Category category) {
		this.id = id;
		this.room = room;
		this.startTime = startTime;
		this.endTime = endTime;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.requestee = requestee;
		this.subject = subject;
		this.category = category;
	}

	public Bookings(BookingsCollection bookingsCollection) {
		this.id = bookingsCollection.getId();
		this.room = bookingsCollection.getRoom();
		this.startTime = bookingsCollection.getStartTime();
		this.endTime = bookingsCollection.getEndTime();
		this.startDate = bookingsCollection.getStartDate();
		this.endDate = bookingsCollection.getEndDate();
		this.status = bookingsCollection.getStatus();
		this.requestee = bookingsCollection.getRequestee();
		this.subject = bookingsCollection.getSubject();
		this.category = bookingsCollection.getCategory();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public User getRequestee() {
		return requestee;
	}
	public void setRequestee(User requestee) {
		this.requestee = requestee;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}


	@Override
	public String toString() {
		return "Bookings [id=" + id + ", room=" + room + ", room1=" + room1 + ", startTime=" + startTime + ", endTime="
				+ endTime + ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status + ", requestee="
				+ requestee + ", subject=" + subject + ", category=" + category + "]";
	}

	public void setRoomCollection(RoomCollection roomCollection) {
		
		room1=roomCollection;
	}
	
	public RoomCollection getRoomCollection()
	{
		return this.room1;
	}
	
	

}
