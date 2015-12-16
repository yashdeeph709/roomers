package com.roommanagement;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RoomAvailabilityTest {

	static Client client;
	String baseURI = "http://localhost:8080/roommanagement";
	WebResource webResource;
	ClientResponse response;
	
	@BeforeClass
	public static void setUp() throws Exception {
		client=Client.create();
	}

	@Test
	public void testAvailabilty() {
		
		webResource=client.resource(baseURI+"/availability/2015-10-06T10:27:56");
		ClientResponse response = webResource.type("application/json").header("authToken","56685db316697f79e253431d").get(ClientResponse.class);
		assertEquals(202,response.getStatus());
	}
	
	@Test
	public void testAvailabiltyWithoutDate() {
		
		webResource=client.resource(baseURI+"/availability/");
		ClientResponse response = webResource.type("application/json").header("authToken","56685db316697f79e253431d").get(ClientResponse.class);
		assertEquals(404,response.getStatus());
	}
	
	@Test
	public void testAvailabilties() {
		
		webResource=client.resource(baseURI+"/availabilities/2015-10-06T10:27:56");
		ClientResponse response = webResource.type("application/json").header("authToken","56685db316697f79e253431d").get(ClientResponse.class);
		assertEquals(202,response.getStatus());
	}
	
	@Test
	public void testAvailabiltiesWithoutDate() {
		
		webResource=client.resource(baseURI+"/availabilities/");
		ClientResponse response = webResource.type("application/json").header("authToken","56685db316697f79e253431d").get(ClientResponse.class);
		assertEquals(404,response.getStatus());
	}

	@Test(expected = AssertionError.class)
	public void testAvailabiltyWithWrongDateFormat() {
		
		webResource=client.resource(baseURI+"/availability/546278436278342");
		ClientResponse response = webResource.type("application/json").header("authToken","56685db316697f79e253431d").get(ClientResponse.class);
		assertEquals(404,response.getStatus());
	}
	
	@Test(expected = AssertionError.class)
	public void testAvailabiltiesWithWrongDateFormat() {
		
		webResource=client.resource(baseURI+"/availabilities/204593-126");
		ClientResponse response = webResource.type("application/json").header("authToken","56685db316697f79e253431d").get(ClientResponse.class);
		assertEquals(404,response.getStatus());
	}
	
}
