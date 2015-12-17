package com.roommanagement;

import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RoomDeleteTest {
	static Client client;
	WebResource webResource;
	String baseURI = "http://localhost:8080/roommanagement";
	ClientResponse response;

	@BeforeClass
	public static void setup() {
		client = Client.create();
	}

	@Test
	public void roomDeleteTest(){
		
		webResource = client.resource(baseURI+"/room/566be6b16db73210abd6239d");
		ClientResponse response = webResource.accept("application/json").header("authToken", "56685db316697f79e253431d").delete(ClientResponse.class);
		int actual=response.getStatus();
		assertEquals(202,actual);
		}	
	
	@Test(expected=NullPointerException.class)
	public void deleteRoomrWithoutIdTest(){
		
		webResource = client.resource(baseURI+"/room");
		ClientResponse response = webResource.accept("application/json").header("authToken","56685db316697f79e253431d").delete(ClientResponse.class);
		
		int actual=response.getStatus();
		assertEquals(201,actual);
			
		}
    }
