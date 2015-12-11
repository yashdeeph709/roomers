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

public class UpdateRoomTest {
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
	public void testUpdateRoom() throws URISyntaxException,JsonParseException, JsonMappingException, IOException  {
		client = Client.create();		
		 webResource = client.resource("http://localhost:8080/RoomManagement/room");
		 String data = "{\"roomName\" : \"Bahar\", \"roomCity\" : \"Mumbai\", \"roomLocation\" : \"Powai\", \"roomBlock\" : \"Hiranandani Business Park\",\"roomAddress\" : \"4th Floor,Nomura,Winchester Building\",\"roomCapacity\" : \"123\",\"roomMachines\" : \"6123\",\"roomBoard\" : \"2\",\"roomChart\" : \"122\", \"roomScreen\" : \"000\",\"roomProjector\" : \"2\",\"roomInternet\" : \"disabled\"}";
		ClientResponse response = webResource.type("application/json").header("authToken",status.getMessage()).post(ClientResponse.class, data);
		
		String expected="{\"status\":\"true\",\"message\":\"Bahar\",\"data\":null,\"dataOne\":null}";
		String actual=response.getEntity(String.class);
		assertEquals(expected,actual);
		}	
	@Test
	public void testUpdateWithNulllValues() throws URISyntaxException,JsonParseException, JsonMappingException, IOException  {
		client = Client.create();		
		 webResource = client.resource("http://localhost:8080/RoomManagement/room");
		 String data = "{ }";
		ClientResponse response = webResource.type("application/json").header("authToken",status.getMessage()).post(ClientResponse.class, data);
		
		String expected="{\"status\":\"false\",\"message\":\"required fields should not be empty\",\"data\":null,\"dataOne\":null}";
		String actual=response.getEntity(String.class);
		assertEquals(expected,actual);
		}	
	
	@Test
	public void testUpdateWithoutRoomName() throws URISyntaxException,JsonParseException, JsonMappingException, IOException  {
		client = Client.create();		
		 webResource = client.resource("http://localhost:8080/RoomManagement/room");
		 String data = "{ \"roomCity\" : \"Mumbai\", \"roomLocation\" : \"Powai\", \"roomBlock\" : \"Hiranandani Business Park\",\"roomAddress\" : \"4th Floor,Nomura,Winchester Building\",\"roomCapacity\" : \"123\",\"roomMachines\" : \"6123\",\"roomBoard\" : \"2\",\"roomChart\" : \"122\", \"roomScreen\" : \"000\",\"roomProjector\" : \"2\",\"roomInternet\" : \"disabled\"}";
		ClientResponse response = webResource.type("application/json").header("authToken",status.getMessage()).post(ClientResponse.class, data);
		
		String expected="{\"status\":\"false\",\"message\":\"required fields should not be empty\",\"data\":null,\"dataOne\":null}";
		String actual=response.getEntity(String.class);
		assertEquals(expected,actual);
		}	
	
	@Test
	public void testUpdateWithoutRoomBlock() throws URISyntaxException,JsonParseException, JsonMappingException, IOException  {
		client = Client.create();		
		 webResource = client.resource("http://localhost:8080/RoomManagement/room");
		 String data = "{ \"roomName\" : \"Bahar\",\"roomCity\" : \"Mumbai\", \"roomLocation\" : \"Powai\",\"roomAddress\" : \"4th Floor,Nomura,Winchester Building\",\"roomCapacity\" : \"123\",\"roomMachines\" : \"6123\",\"roomBoard\" : \"2\",\"roomChart\" : \"122\", \"roomScreen\" : \"000\",\"roomProjector\" : \"2\",\"roomInternet\" : \"disabled\"}";
		ClientResponse response = webResource.type("application/json").header("authToken",status.getMessage()).post(ClientResponse.class, data);
		
		String expected="{\"status\":\"false\",\"message\":\"required fields should not be empty\",\"data\":null,\"dataOne\":null}";
		String actual=response.getEntity(String.class);
		assertEquals(expected,actual);
		}	
	
	@Test
	public void testUpdateWithoutRoomLocation() throws URISyntaxException,JsonParseException, JsonMappingException, IOException  {
		client = Client.create();		
		 webResource = client.resource("http://localhost:8080/RoomManagement/room");
		 String data = "{ \"roomName\" : \"Bahar\",\"roomCity\" : \"Mumbai\", \"roomBlock\" : \"Hiranandani Business Park\",\"roomAddress\" : \"4th Floor,Nomura,Winchester Building\",\"roomCapacity\" : \"123\",\"roomMachines\" : \"6123\",\"roomBoard\" : \"2\",\"roomChart\" : \"122\", \"roomScreen\" : \"000\",\"roomProjector\" : \"2\",\"roomInternet\" : \"disabled\"}";
		ClientResponse response = webResource.type("application/json").header("authToken",status.getMessage()).post(ClientResponse.class, data);
		
		String expected="{\"status\":\"false\",\"message\":\"required fields should not be empty\",\"data\":null,\"dataOne\":null}";
		String actual=response.getEntity(String.class);
		assertEquals(expected,actual);
		}	
}
