package com.roommanagement;


import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Before;
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

public class BookingTest {

	Client client;
	String baseURI = "http://localhost:8080/roommanagement";
	WebResource webResource;
	ClientResponse response;
	
	@Before
	public void setUp() throws Exception {
		
		client=Client.create();
		
	}
	
	@Test
	public void testRoomCreation() throws JsonParseException, JsonMappingException, IOException {		

		webResource=client.resource(baseURI+"/booking/566c18890f9dadffad29c068");
		String data ="{\"startDate\": \"2016-12-11T10:18:06.782Z\",\"endDate\": \"2016-12-11T18:18:16.782Z\",\"status\": \"REQUESTED\",\"requestee\": \"nihit@gmail.com\",\"subject\": \".Net\",\"category\": \"OPEN_PROGRAM\"}";
		ClientResponse response = webResource.type("application/json").header("authToken","56685db316697f79e253431d").post(ClientResponse.class,data);
		assertEquals(201,response.getStatus());
	}
	
	
}
