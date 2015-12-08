package com.roommanagement;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roommanagement.beans.UserBean;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RestTest {

	@Before
	public void setup(){
	}
	
	@Test
	public void test() throws JsonParseException, JsonMappingException, IOException {
		Client client = Client.create();
		WebResource webResource = client.resource("http://localhost:8080/getUsers");
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
		ObjectMapper mapper=new ObjectMapper();
		String output = response.getEntity(String.class);
		UserBean[] users=mapper.readValue(output,UserBean[].class);
		assertNotEquals(users.length,0);
	}
}
