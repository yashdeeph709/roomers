package com.roommanagement;


import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class BookingCancellationTest {

	static Client client;
	String baseURI = "http://localhost:8080/roommanagement/booking/cancel";
	WebResource webResource;
	ClientResponse response;
	
	@BeforeClass
	public static void setUp() throws Exception {
		
		client=Client.create();
		
	}
	
	/************testBookingCancellationByAdmin**********************/
	@Test
	public void testBookingCancellationByAdmin() throws JsonParseException, JsonMappingException, IOException {		

		webResource=client.resource(baseURI);
		String data ="{\"id\":\"566e841fb3998d1214061389\"}";
		ClientResponse response = webResource.type("application/json").header("authToken","56685db316697f79e253431d").put(ClientResponse.class,data);
		assertEquals(202,response.getStatus());
	}
	
	/************testBookingCancellationByAdminWithInvalidId**********************/
	@Test
	public void testBookingCancellationByAdminWithInvalidId() throws JsonParseException, JsonMappingException, IOException {		

		webResource=client.resource(baseURI);
		String data ="{\"id\":\"10000\"}";
		ClientResponse response = webResource.type("application/json").header("authToken","56685db316697f79e253431d").put(ClientResponse.class,data);
		assertEquals(204,response.getStatus());
	}
	
	/************testBookingCancellationByUser**********************/
	public void testRoomCancellationByUser() {
		client = Client.create();
		webResource = client.resource(baseURI+"566c18890f9dadffad29c068");
		ClientResponse response = webResource.accept("application/json").header("authToken", "Bahar").delete(ClientResponse.class);
		assertEquals(202,response.getStatus());
		}

	/************testBookingCancellationByUserWithInvalidId**********************/
	public void testRoomCancellationByUserWithInvalidId() {
		client = Client.create();
		webResource = client.resource(baseURI+"00000");
		ClientResponse response = webResource.accept("application/json").header("authToken", "Bahar").delete(ClientResponse.class);
		assertEquals(204,response.getStatus());
		}
	
	
}
