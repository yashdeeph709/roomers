package com.roommanagement;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roommanagement.beans.Status;
import com.roommanagement.collections.RoomCollection;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RoomTests {

	Client client;
	String baseURI = "http://localhost:8080/RoomManagement";
	WebResource webResource;
	Status<RoomCollection> status=null;
	ClientResponse response;
	
	@Before
	public void setUp() throws Exception {
		
		client=Client.create();
		webResource = client.resource(baseURI+"/getAdmin");
		response = webResource.accept("application/json").get(ClientResponse.class);
		
		String output = response.getEntity(String.class);
		ObjectMapper mapper=new ObjectMapper();
		try {
			status=mapper.readValue(output,Status.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void testRoomCreation() throws JsonParseException, JsonMappingException, IOException {

		client = Client.create();		

		webResource=client.resource(baseURI+"/createRoom");
		
		String roomCollection ="{\"roomName\":\"Bahar\",\"roomCity\":\"Pune\",\"roomLocation\":\"Baner Gaon\",\"roomBlock\":\"Baner\",\"roomAddress\":\"5th Floor Amar Paradigm\",\"roomCapacity\":16,\"roomTables\":4,\"roomMachines\":16,\"roomScreen\":1,\"roomBoard\":4,\"roomChart\":1,\"roomProjector\":1,\"roomInternet\":\"false\"}";
		ClientResponse response = webResource.type("application/json").header("authToken",status.getMessage()).post(ClientResponse.class,roomCollection);
		
		String output = response.getEntity(String.class);
		ObjectMapper mapper=new ObjectMapper();
		
		Status<RoomCollection> s=mapper.readValue(output,Status.class);
		assertEquals("Bahar" ,s.getMessage());
	}
	
	@Test
	public void testRoomDaoWithNullValues() throws JsonParseException, JsonMappingException, IOException {
		
		webResource=client.resource(baseURI+"/createRoom");
		
		String roomCollection = "{\"roomName\":null,\"roomCity\":null,\"roomLocation\":null,\"roomBlock\":null,\"roomAddress\":null,\"roomCapacity\":null,\"roomTables\":null,\"roomMachines\":null,\"roomScreen\":null,\"roomBoard\":null,\"roomChart\":null,\"roomProjector\":null,\"roomInternet\":null}";

		ClientResponse response = webResource.type("application/json").header("authToken",status.getMessage()).post(ClientResponse.class,roomCollection);
		
		String output = response.getEntity(String.class);
		ObjectMapper mapper=new ObjectMapper();
		
		Status<RoomCollection> s=mapper.readValue(output,Status.class);
		assertEquals(null,s.getMessage());
	}

}
