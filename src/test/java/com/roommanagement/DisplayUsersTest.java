package com.roommanagement;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roommanagement.beans.Status;
import com.roommanagement.beans.UserBean;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class DisplayUsersTest {
	Status status=null;
	
	@Before
	public void setup(){
		Client client = Client.create();
		WebResource webResource = client.resource("http://localhost:8080/RoomManagement/getAdmin");
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		String output = response.getEntity(String.class);
		ObjectMapper mapper=new ObjectMapper();
		try {
			status=mapper.readValue(output,Status.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test() throws JsonParseException, JsonMappingException, IOException {
		Client client = Client.create();		
		WebResource webResource = client.resource("http://localhost:8080/getUsers");
		webResource.header("authToken",status.getMessage());
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed Test : HTTP error code : " + response.getStatus());
		}
		ObjectMapper mapper=new ObjectMapper();
		String output = response.getEntity(String.class);
		System.out.println(output);
	}
}
