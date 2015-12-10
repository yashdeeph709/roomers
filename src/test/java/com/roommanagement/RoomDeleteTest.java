package com.roommanagement;

import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import com.roommanagement.beans.Room;
import com.roommanagement.services.RoomService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RoomDeleteTest {
	Room room;
	RoomService roomService;
	JSONObject obj = new JSONObject();
	Client client ;
	WebResource webResource;
	String baseURI;
	
	@Before
	public void setup()
	{
		client = Client.create();
		 baseURI = "http://localhost:8080/RoomManagement/deleteRoom";
		
	}
	@Test
	public void deleteRoomTest(){
		webResource = client.resource(baseURI+"/Gandhi");
		ClientResponse response = webResource.type("application/json").get(ClientResponse.class);
		String output=response.getEntity(String.class);
		System.out.println(output);
		assertEquals(output,"{\"status\":\"success\",\"message\":\"Room Deleted Successfully!\"}");
			
		}	
	
	

}
