package com.roommanagement;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpHeaders;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roommanagement.beans.Status;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;



public class CreateUserTest {

	Client client ;
	WebResource webResource;
	String baseURI = "http://localhost:8080/RoomManagement";
	ClientResponse response;
	Status status=null;
	HttpHeaders httpHeader;
	@Before
	public void setup(){
		client = Client.create();
		webResource = client.resource("http://localhost:8080/roommanagement/login");
/*		String data="{\"email\":\"shrutiu.7@gmail.com\",\"password\":\"password\"}";
		response = webResource.accept("application/json").post(ClientResponse.class,data);
		String output = response.getEntity(String.class);
		ObjectMapper mapper=new ObjectMapper();
		try {
			//status="Done";//mapper.readValue(output,Status.class);
			System.out.println(output);
			httpHeader=new HttpHeaders();
		} catch (Exception e) {
			e.printStackTrace();
		}
*/	}
	
	
	
	@Test
	public void testCreateUser() throws URISyntaxException,JsonParseException, JsonMappingException, IOException  {
		
		client = Client.create();		
		 webResource = client.resource("http://localhost:8080/roommanagement/users");
		String data = "{\"name\":\"lily\",\"email\":\"lily@gmail.com\",\"password\":\"Palakh\",\"rights\":1}";

		ClientResponse response = webResource.type("application/json").header("authToken","56685db316697f79e253431d").post(ClientResponse.class, data);
		int expected=401; 
		int actual=response.getStatus();
		assertEquals(expected,actual );
	}
	
	@Test
	public void testCreateUserWithNullValues() throws URISyntaxException {
		
		client = Client.create();		
		 webResource = client.resource("http://localhost:8080/roommanagement/users");
		String data = "{\"name\":\"null\",\"email\":\"null\",\"password\":\"null\",\"rights\":0}";

		ClientResponse response = webResource.type("application/json").header("authToken","56685db316697f79e253431d").post(ClientResponse.class, data);
		String expected="{\"status\":\"true\",\"message\":\"Created User name null\",\"data\":null,\"dataOne\":null}";
		String actual=response.getEntity(String.class);
		assertEquals(401,response.getStatus());
	}

}
