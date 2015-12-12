package com.roommanagement;

import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roommanagement.beans.Status;
import com.roommanagement.beans.User;
import com.roommanagement.services.UserService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


public class AdminDeleteUserTest {
	User userBean;
	UserService userService;
	JSONObject obj = new JSONObject();
	Client client ;
	WebResource webResource; 
	String baseURI = "http://localhost:8080/RoomManagement"; 
	ClientResponse response;
	Status status=null;
	@Before
	public void setup()
	{
		client = Client.create();
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
	public void deleteUserTest(){
		
		webResource = client.resource(baseURI+"/users/56685dd016697f79e253431e");
		ClientResponse response = webResource.accept("application/json").header("authToken", status.getMessage()).get(ClientResponse.class);
		String expected="{\"status\":\"success\",\"message\":\"User Deleted Successfully!\",\"data\":null,\"dataOne\":null}";
		String actual=response.getEntity(String.class);
		assertEquals(expected,actual);
		}	
	
	@Test
	public void deleteUserWithoutIdTest(){
		webResource = client.resource(baseURI+"/users");
		ClientResponse response = webResource.accept("application/json").header("authToken", status.getMessage()).get(ClientResponse.class);
		String expected="{\"status\":\"failed\",\"message\":\"User Deleted Successfully!\",\"data\":null,\"dataOne\":null}";
		String actual=response.getEntity(String.class);
		assertEquals(expected,actual);
			
		}
    }
