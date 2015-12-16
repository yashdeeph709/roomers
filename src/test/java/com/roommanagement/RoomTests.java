package com.roommanagement;


import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RoomTests {

	static Client client;
	String baseURI = "http://localhost:8080/roommanagement";
	WebResource webResource;
	ClientResponse response;
	
	@BeforeClass
	public static void setUp() throws Exception {
		
		client=Client.create();
		
	}
	
	@Test
	public void testRoomCreation() throws JsonParseException, JsonMappingException, IOException {		

		webResource=client.resource(baseURI+"/rooms");
		String roomCollection ="{\"roomName\":\"Bahar2\",\"roomCity\":\"Pune\",\"roomLocation\":\"Baner Gaon\",\"roomBlock\":\"Baner\",\"roomAddress\":\"5th Floor Amar Paradigm\",\"roomCapacity\":16,\"roomTables\":4,\"roomMachines\":16,\"roomScreen\":1,\"roomBoard\":4,\"roomChart\":1,\"roomProjector\":1,\"roomInternet\":\"false\"}";
		ClientResponse response = webResource.type("application/json").header("authToken","56685db316697f79e253431d").post(ClientResponse.class,roomCollection);
		assertEquals(201,response.getStatus());
	}
	
	
	@Test
	public void testRoomCreationWithNullValues() throws JsonParseException, JsonMappingException, IOException {
		
		webResource=client.resource(baseURI+"/rooms");
		String roomCollection = "{\"roomName\":null,\"roomCity\":null,\"roomLocation\":null,\"roomBlock\":null,\"roomAddress\":null,\"roomCapacity\":null,\"roomTables\":null,\"roomMachines\":null,\"roomScreen\":null,\"roomBoard\":null,\"roomChart\":null,\"roomProjector\":null,\"roomInternet\":null}";
		ClientResponse response = webResource.type("application/json").header("authToken","56685db316697f79e253431d").post(ClientResponse.class,roomCollection);	
		assertEquals(400,response.getStatus());
	}
	
	
	@Test
	public void testdeleteRoom() {
		
		webResource = client.resource(baseURI+"/rooms/566c198e0f9dadffad29c06f");
		ClientResponse response = webResource.accept("application/json").header("authToken","56685db316697f79e253431d").delete(ClientResponse.class);
		assertEquals(202,response.getStatus());
		
	}
	
	
	
}
