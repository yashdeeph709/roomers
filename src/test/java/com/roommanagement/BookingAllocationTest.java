package com.roommanagement;


import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roommanagement.beans.Bookings;
import com.roommanagement.collections.RoomCollection;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class BookingAllocationTest {

	static Client client;
	String baseURI = "http://localhost:8080/roommanagement/booking";
	WebResource webResource;
	ClientResponse response;
	
	@BeforeClass
	public static void setUp() throws Exception {
		
		client=Client.create();
		
	}
	
	/************testBookingAllocation**********************/
	@Test
	public void testBookingAllocation() throws JsonParseException, JsonMappingException, IOException {		

		webResource=client.resource(baseURI);
		String data ="{\"id\":\"566e841fb3998d1214061389\"}";
		ClientResponse response = webResource.type("application/json").header("authToken","56685db316697f79e253431d").put(ClientResponse.class,data);
		assertEquals(202,response.getStatus());
	}
	
	/************testBookingAllocationWithInvalidId**********************/
	@Test
	public void testBookingAllocationWithInvalidId() throws JsonParseException, JsonMappingException, IOException {		

		webResource=client.resource(baseURI);
		String data ="{\"id\":\"10000\"}";
		ClientResponse response = webResource.type("application/json").header("authToken","56685db316697f79e253431d").put(ClientResponse.class,data);
		assertEquals(204,response.getStatus());
	}
	

	
	
}
