package com.roommanagement;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roommanagement.beans.Status;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;



public class CreateUserTest {

	Client client ;
	WebResource webResource;
	String baseURI = "http://localhost:8080/RoomManagement";
	ClientResponse response;
	Status status=null;
	@Before
	public void setup(){
		client = Client.create();
		webResource = client.resource("http://localhost:8080/RoomManagement/getAdmin");
		response = webResource.accept("application/json").get(ClientResponse.class);
		String output = response.getEntity(String.class);
		ObjectMapper mapper=new ObjectMapper();
		try {
			status=mapper.readValue(output,Status.class);
			System.out.println(status);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void testCreateUser() throws URISyntaxException,JsonParseException, JsonMappingException, IOException  {
		
		client = Client.create();		
		 webResource = client.resource("http://localhost:8080/RoomManagement/register");
		String expected = "{\"name\":\"Palakh\",\"email\":\"palakh@gmail.com\",\"password\":\"Palakh\",\"rights\":\"1\"}";

		ClientResponse response = webResource.type("application/json").header("authToken",status.getMessage()).post(ClientResponse.class, expected);
		 String actual=response.getEntity(String.class);
		System.out.println(actual);
		assertEquals(actual, "{\"status\":\"NotAuthenticated\",\"message\":\"User not Authenticated\",\"dataOne\":null,\"data\":null}");
	}
	
	@Test
	public void testCreateUserWithNullValues() throws URISyntaxException {
		
		String expected = "{\"name\":null,\"email\":null,\"password\":null,\"rights\":null}";
		client = Client.create();		
		 webResource = client.resource("http://localhost:8080/RoomManagement/register");
		

		ClientResponse response = webResource.type("application/json").header("authToken",status.getMessage()).post(ClientResponse.class, expected);
		
		 String actual=response.getEntity(String.class);
		System.out.println(actual);
		assertEquals(actual, "{\"status\":\"true\",\"message\":\"Created User name =null\"}");
	}

}
