package com.roommanagement;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class UpdateRoomTest {
	Client client ;
	WebResource webResource;
	String baseURI = "http://localhost:8080/roommanagement";
	ClientResponse response;
	@Before
	public void setup(){
		client = Client.create();
//		webResource = client.resource("http://localhost:8080/RoomManagement/getAdmin");
//		response = webResource.accept("application/json").get(ClientResponse.class);
//		String output = response.getEntity(String.class);
//		ObjectMapper mapper=new ObjectMapper();
	}
	
	
	
	@Test
	public void testUpdateRoom() throws URISyntaxException,JsonParseException, JsonMappingException, IOException  {
				
		 webResource = client.resource("http://localhost:8080/roommanagement/rooms");
		 String data = "{\"id\" : \"566c18890f9dadffad29c068\",\"roomName\" : \"Bahar\",\"roomCity\" : \"Pune\",\"roomLocation\" : \"Baner Gaon\",\"roomBlock\" : \"Baner\",\"roomAddress\" : \"5th Floor Amar paradigm\",\"roomCapacity\" : 16,\"roomTables\" : 4,\"roomMachines\" : 16,\"roomBoard\" : 4,\"roomChart\" : 1,\"roomScreen\" : 1,\"roomProjector\" : 1,\"roomInternet\" : true}";
		ClientResponse response = webResource.type("application/json").header("authToken","56685db316697f79e253431d").put(ClientResponse.class, data);
		int expected=202;
		int actual=response.getStatus();
		assertEquals(expected,actual);
		}	
	@Test
	public void testUpdateWithNulllValues() throws URISyntaxException,JsonParseException, JsonMappingException, IOException  {
				
		webResource = client.resource("http://localhost:8080/roommanagement/rooms");
		 String data = "{ \"roomCity\" : \"Mumbai\", \"roomLocation\" : \"Powai\", \"roomBlock\" : \"Hiranandani Business Park\",\"roomAddress\" : \"4th Floor,Nomura,Winchester Building\",\"roomCapacity\" : \"123\",\"roomMachines\" : \"6123\",\"roomBoard\" : \"2\",\"roomChart\" : \"122\", \"roomScreen\" : \"000\",\"roomProjector\" : \"2\",\"roomInternet\" : \"false\"}";
		ClientResponse response = webResource.type("application/json").header("authToken","56685db316697f79e253431d").post(ClientResponse.class, data);
		int expected=400;
		int actual=response.getStatus();
		assertEquals(expected,actual);
		}	
	
	@Test
	public void testUpdateWithoutRoomName() throws URISyntaxException,JsonParseException, JsonMappingException, IOException  {
				
		webResource = client.resource("http://localhost:8080/roommanagement/rooms");
		 String data = "{ \"roomCity\" : \"Mumbai\", \"roomLocation\" : \"Powai\", \"roomBlock\" : \"Hiranandani Business Park\",\"roomAddress\" : \"4th Floor,Nomura,Winchester Building\",\"roomCapacity\" : \"123\",\"roomMachines\" : \"6123\",\"roomBoard\" : \"2\",\"roomChart\" : \"122\", \"roomScreen\" : \"000\",\"roomProjector\" : \"2\",\"roomInternet\" : \"false\"}";
		ClientResponse response = webResource.type("application/json").header("authToken","56685db316697f79e253431d").post(ClientResponse.class, data);
		int expected=400;
		int actual=response.getStatus();
		assertEquals(expected,actual);
		}	
	
	@Test
	public void testUpdateWithoutRoomBlock() throws URISyntaxException,JsonParseException, JsonMappingException, IOException  {
				
		webResource = client.resource("http://localhost:8080/roommanagement/rooms");
		 String data = "{ \"roomName\" : \"Bahar\",\"roomCity\" : \"Mumbai\", \"roomLocation\" : \"Powai\",\"roomAddress\" : \"4th Floor,Nomura,Winchester Building\",\"roomCapacity\" : \"123\",\"roomMachines\" : \"6123\",\"roomBoard\" : \"2\",\"roomChart\" : \"122\", \"roomScreen\" : \"000\",\"roomProjector\" : \"2\",\"roomInternet\" : \"false\"}";
		ClientResponse response = webResource.type("application/json").header("authToken","56685db316697f79e253431d").post(ClientResponse.class, data);
		
		int expected=400;
		int actual=response.getStatus();
		assertEquals(expected,actual);
		}	
	
	@Test
	public void testUpdateWithoutRoomLocation() throws URISyntaxException,JsonParseException, JsonMappingException, IOException  {
				
		webResource = client.resource("http://localhost:8080/roommanagement/rooms");
		 String data = "{ \"roomName\" : \"Bahar\",\"roomCity\" : \"Mumbai\", \"roomBlock\" : \"Hiranandani Business Park\",\"roomAddress\" : \"4th Floor,Nomura,Winchester Building\",\"roomCapacity\" : \"123\",\"roomMachines\" : \"6123\",\"roomBoard\" : \"2\",\"roomChart\" : \"122\", \"roomScreen\" : \"000\",\"roomProjector\" : \"2\",\"roomInternet\" : \"false\"}";
		ClientResponse response = webResource.type("application/json").header("authToken","56685db316697f79e253431d").post(ClientResponse.class, data);
		int expected=400;
		int actual=response.getStatus();
		assertEquals(expected,actual);
		}	
}
