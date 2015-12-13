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

public class DisplayUsersTest {

	
	@Before
	public void setup(){
		Client client = Client.create();
		WebResource webResource = client.resource("http://localhost:8080/RoomManagement/login");
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
	}
	
	@Test
	public void test() throws JsonParseException, JsonMappingException, IOException {
		Client client = Client.create();		
		WebResource webResource = client.resource("http://localhost:8080/RoomManagement/users");
		ClientResponse response = webResource.accept("application/json").header("authToken","56685db316697f79e253431d").get(ClientResponse.class);
		int output = response.getStatus();
		assertNotEquals(202,output);
	}
}
