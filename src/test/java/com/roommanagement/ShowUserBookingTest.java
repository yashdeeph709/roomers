package com.roommanagement;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ShowUserBookingTest {
	Client client;
	String baseURI = "http://localhost:8080/roommanagement";
	WebResource webResource;
	ClientResponse response;

	@Before
	public void setUp() throws Exception {

		client = Client.create();

	}

	@Test
	public void userBookingTest() throws JsonParseException, JsonMappingException, IOException {

		webResource = client.resource(baseURI + "/booking");

		ClientResponse response = webResource.accept("application/json").header("authToken", "harshal@gmail.com")
				.get(ClientResponse.class);
		assertEquals(202, response.getStatus());
	}
}