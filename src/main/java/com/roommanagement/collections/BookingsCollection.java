package com.roommanagement.collections;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.roommanagement.beans.Bookings;
import com.roommanagement.beans.Category;
import com.roommanagement.beans.Room;
import com.roommanagement.beans.Status;
import com.roommanagement.beans.User;

@Document(collection="bookings")
public class BookingsCollection {
	
	@Id
	private String id;
	private Room room;
	private Date startTime;
	private Date endTime;
	private Date startDate;
	private Date endDate;
	private Status status;
	private User requestee;
	private String subject;
	private Category category;
	public BookingsCollection()
	{
		
	}
	public BookingsCollection(String id, Room room, Date startTime, Date endTime, Date startDate, Date endDate, Status status,
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

	public BookingsCollection(Bookings booking) {
		this.id = booking.getId();
		this.room = booking.getRoom();
		this.startTime = booking.getStartTime();
		this.endTime = booking.getEndTime();
		this.startDate = booking.getStartDate();
		this.endDate = booking.getEndDate();
		this.status = booking.getStatus();
		this.requestee = booking.getRequestee();
		this.subject = booking.getSubject();
		this.category = booking.getCategory();
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
		return "Bookings [room=" + room + ", startTime=" + startTime + ", endTime=" + endTime + ", startDate="
				+ startDate + ", endDate=" + endDate + ", status=" + status + ", requestee=" + requestee + ", subject="
				+ subject + ", category=" + category + "]";
	}
	
}
