package com.roommanagement;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


public class RoomDeleteTest {
	Client client;
	WebResource webResource;
	String baseURI = "http://localhost:8080/roommanagement";
	ClientResponse response;

	@Before
	public void setup() {
		client = Client.create();
	}

	@Test
	public void testdeleteRoom() {
		client = Client.create();
		webResource = client.resource("http://localhost:8080/roommanagement/room/566a580e63c7c445a5d2863c");
		ClientResponse response = webResource.accept("application/json").header("authToken","Bahar")
				.delete(ClientResponse.class);
		String expected = "";
		String actual = response.getEntity(String.class);
		assertEquals(expected,actual);
	}
}