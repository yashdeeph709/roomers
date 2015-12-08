package com.roommanagement;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RoomTests {

	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testRoomDao() {
		
		Client client=Client.create();
		WebResource webResource=client.resource("http://localhost:8080/createRoom");
		
		String roomCollection = "{\"roomName\":\"Bahar\",\"roomCity\":\"Pune\",\"roomLocation\":\"Baner Gaon\",\"roomBlock\":\"Baner\",\"roomAddress\":\"5th Floor Amar Paradigm\",\"roomCapacity\":16,\"roomTables\":4,\"roomMachines\":16,\"roomScreen\":1,\"roomBoard\":4,\"roomChart\":1,\"roomProjector\":1,\"roomInternet\":\"false\"}";

		ClientResponse response = webResource.type("application/json")
		   .post(ClientResponse.class,roomCollection);

		
		String output = response.getEntity(String.class);
		assertEquals("{\"status\":\"success\",\"message\":\"room created sucessfully\"}",output);
	}

}
