package com.roommanagement.services;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.roommanagement.beans.Bookings;
import com.roommanagement.beans.Email;
import com.roommanagement.beans.Room;
import com.roommanagement.beans.Status;
import com.roommanagement.collections.BookingsCollection;
import com.roommanagement.collections.RoomCollection;
import com.roommanagement.collections.UserCollection;
import com.roommanagement.repository.BookingsRepository;
import com.roommanagement.repository.UsersRepository;

@Service
public class BookingsServiceImpl implements BookingsService {

	@Autowired
	private BookingsRepository bookingsRepository;
	@Autowired
	AvailabilityService availabilityService;
	@Autowired
	private UsersRepository userRepository;
	@Autowired
	private MongoOperations mongoOperations;
	private BasicQuery basicQuery;
	private Bookings allocatedRoom;
	private HttpHeaders httpHeaders = new HttpHeaders();

	public Bookings bookRoom(Bookings booking,String roomId) {
		
		
		
		if(roomId==null){
			
			return null;			//If Bookings is not inserted
		}
		else{
		
		basicQuery= new BasicQuery("{ \"id\" : \""+roomId+"\" }");
		RoomCollection roomCollection=mongoOperations.findOne(basicQuery,RoomCollection.class);
		Room room=new Room(roomCollection);
		booking.setRoom(room);
		
		Query query = new Query(Criteria.where("startDate").in(booking.getStartDate()).and("requestee").in(booking.getRequestee()));
	
		BookingsCollection alreadyRequested=mongoOperations.findOne(query,BookingsCollection.class);
		
		if(alreadyRequested!=null)
		{
			return null;
		}
		
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(booking.getStartDate());
		calendar.add(Calendar.HOUR,+1 );
		booking.setEndDate(calendar.getTime());
		System.out.println(booking.getEndDate());
		
		
		
		return new Bookings(bookingsRepository.insert(new BookingsCollection(booking)));
		
		
		}
	}
	

	
	public List<Bookings> getMyBookings(String authToken) {
		BasicQuery basicQuery = new BasicQuery("{ requestee : \"" + authToken + "\"}");
		List<BookingsCollection> bookingsCollectionList = mongoOperations.find(basicQuery, BookingsCollection.class);
		List<Bookings> requiredBookings = new ArrayList<Bookings>();
		if(bookingsCollectionList.size()==0)
		{
			return null;
		}
		for (BookingsCollection bookingCollection : bookingsCollectionList) {
			requiredBookings.add(new Bookings(bookingCollection));
		}
		return requiredBookings;
	}

	public List<Bookings> getMyBookingsRange(String authToken, int start, int end) {

		BasicQuery basicQuery = new BasicQuery("{ requestee : \"" + authToken +" \"}");
		basicQuery.skip(start);
		basicQuery.limit(end);
		List<BookingsCollection> bookingsCollectionList = mongoOperations.find(basicQuery, BookingsCollection.class);
		List<Bookings> requiredBookings = new ArrayList<Bookings>();
		for (BookingsCollection bookingCollection : bookingsCollectionList) {
			requiredBookings.add(new Bookings(bookingCollection));
		}
		return requiredBookings;
	}

	public Bookings allocateRoom(Bookings requestedBooking){
		BasicQuery basicQuery= new BasicQuery("{ \"id\" : \""+requestedBooking.getId()+"\" }");
		BookingsCollection booking=mongoOperations.findOne(basicQuery,BookingsCollection.class);
		if(booking!=null)
		{	
			booking.setId(requestedBooking.getId()); 	
			booking.setStatus(Status.BOOKED);
			allocatedRoom=new Bookings(bookingsRepository.save(booking));
			UserCollection requestee=userRepository.findOne(requestedBooking.getRequestee());
						ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("Mail-bean.xml");
			Email sendMail = (Email) context.getBean("mailMail");
			sendMail.sendMail("shrutiu.7@gmail.com",
								requestee.getEmail(),
							  "Room Booking Acceptance", 
							  "Dear "+requestee.getName()+",\n\nYour Booking request on "+allocatedRoom.getStartDate()+" for the Room \""+allocatedRoom.getRoom().getRoomName()+"\" has  been Accepted  by Admin.");
			return allocatedRoom;
		}
		return null;

	}
	
	/*****************Room Cancellation By Admin*****************/
	public Bookings roomCancellation(Bookings requestedBooking){
		BasicQuery basicQuery= new BasicQuery("{ \"id\" : \""+requestedBooking.getId()+"\" }");
		BookingsCollection booking=mongoOperations.findOne(basicQuery,BookingsCollection.class);
		if(booking!=null)
		{	
			booking.setId(requestedBooking.getId()); 	
			booking.setStatus(Status.CANCELLED);
			allocatedRoom=new Bookings(bookingsRepository.save(booking));
			
			UserCollection requestee=userRepository.findOne(requestedBooking.getRequestee());
			ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("Mail-bean.xml");
			Email sendMail = (Email) context.getBean("mailMail");
			sendMail.sendMail("shrutiu.7@gmail.com",
								requestee.getEmail(),
							  "Room Booking Cancellation", 
							  "Dear "+requestee.getName()+",\n\nYour Booking request on "+allocatedRoom.getStartDate()+" for the Room \""+allocatedRoom.getRoom().getRoomName()+"\" has  been cancelled by Admin.");
			return allocatedRoom;
		}
		return null;

	}
	
	/************Room Cancellation by user**************/
	public void cancel(String name) {
		bookingsRepository.delete(name);
	}

	
	/************getStatus****************/

	public ResponseEntity<Bookings> getStatus(Bookings bookingReturned)
	{
		if(bookingReturned == null){
			return new ResponseEntity<Bookings>(bookingReturned, httpHeaders,HttpStatus.BAD_REQUEST);
		}
		else{
			return new ResponseEntity<Bookings>(bookingReturned, httpHeaders,HttpStatus.CREATED);					//If Bookings is created
		}
		
	}
	
	public ResponseEntity<List<Bookings>> getStatus(List<Bookings> bookings)
	{
		if(bookings==null){
			return new ResponseEntity<List<Bookings>>(bookings, httpHeaders, HttpStatus.NO_CONTENT); 				//If no rooms are there in the db 
		}
		else{
			return new ResponseEntity<List<Bookings>>(bookings, httpHeaders, HttpStatus.ACCEPTED);				//If room are there
		}
		
	}




	public ResponseEntity<String> getStatus(Bookings allocateRoom, HttpHeaders httpHeaders) {
		if(allocateRoom==null)
		{
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}



	public List<Bookings> getBookingRequests() {
		List<BookingsCollection> bookingsCollectionList =bookingsRepository.findAll();
		List<Bookings> requiredBookings = new ArrayList<Bookings>();
		for (BookingsCollection bookingCollection : bookingsCollectionList) {
			requiredBookings.add(new Bookings(bookingCollection));
		}
		return requiredBookings;
		
	}
	



}
