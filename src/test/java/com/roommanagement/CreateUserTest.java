package com.roommanagement;

import static org.junit.Assert.assertEquals;

import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;

import com.roommanagement.beans.Status;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;



public class CreateUserTest {

	Client client ;
	WebResource webResource;
	String baseURI = "http://localhost:8080/RoomManagement";
	@Before
	public void setup()
	{
		client = Client.create();
		webResource = client.resource(baseURI+"/register");
	}
	
	@Test
	public void testCreateUser() throws URISyntaxException {
		
		String expected = "{\"name\":\"Palakh\",\"email\":\"palakh@gmail.com\",\"password\":\"Palakh\",\"rights\":\"1\"}";

		ClientResponse response = webResource.type("application/json").post(ClientResponse.class, expected);
		 String actual=response.getEntity(String.class);
		System.out.println(actual);
		assertEquals(actual, "{\"status\":\"true\",\"message\":\"Created User name =Palakh\"}");
	}
	
	@Test
	public void testCreateUserWithNullValues() throws URISyntaxException {
		
		String expected = "{\"name\":null,\"email\":null,\"password\":null,\"rights\":null}";

		ClientResponse response = webResource.type("application/json").post(ClientResponse.class, expected);
		 String actual=response.getEntity(String.class);
		System.out.println(actual);
		assertEquals(actual, "{\"status\":\"true\",\"message\":\"Created User name =null\"}");
	}

}
