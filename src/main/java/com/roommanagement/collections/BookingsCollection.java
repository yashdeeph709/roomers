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
	private Date startDate;
	private Date endDate;
	private Status status;
	private String requestee;
	private String subject;
	private Category category;
	private Date dateTest;

	public BookingsCollection()
	{
		
	}
	public BookingsCollection(Bookings booking) {
	this.id = booking.getId();
	this.room = booking.getRoom();
	this.startDate = booking.getStartDate();
	this.endDate = booking.getEndDate();
	this.status = booking.getStatus();
	this.requestee = booking.getRequestee();
	this.subject = booking.getSubject();
	this.category = booking.getCategory();

}
	
	
	public BookingsCollection(String id, Room room, Date startDate, Date endDate, Status status, String requestee,
			String subject, Category category, Date dateTest) {
		this.id = id;
		this.room = room;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.requestee = requestee;
		this.subject = subject;
		this.category = category;
		this.dateTest = dateTest;
	}
	public void setRequestee(String requestee) {
		this.requestee = requestee;
	}


	public String getRequestee() {
		return requestee;
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
	public Date getDateTest() {
		return dateTest;
	}
	public void setDateTest(Date dateTest) {
		this.dateTest = dateTest;
	}
	@Override
	public String toString() {
		return "BookingsCollection [id=" + id + ", room=" + room + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", status=" + status + ", requestee=" + requestee + ", subject=" + subject + ", category=" + category
				+ ", dateTest=" + dateTest + "]";
	}
	
}
