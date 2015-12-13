package com.roommanagement;

import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
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
	String baseURI = "http://localhost:8080/roommanagement"; 
	ClientResponse response;
	@Before
	public void setup()
	{
		client = Client.create();
	

	}
	
	
	@Test
	public void deleteUserTest(){
		
		webResource = client.resource(baseURI+"/users/566be6b16db73210abd6239d");
		ClientResponse response = webResource.accept("application/json").header("authToken", "56685db316697f79e253431d").delete(ClientResponse.class);
		String expected="{\"status\":\"success\",\"message\":\"User Deleted Successfully!\",\"data\":null,\"dataOne\":null}";
		int actual=response.getStatus();
		assertEquals(202,actual);
		}	
	
	@Test(expected=NullPointerException.class)
	public void deleteUserWithoutIdTest(){
		
		webResource = client.resource(baseURI+"/users");
		ClientResponse response = webResource.accept("application/json").header("authToken","56685db316697f79e253431d").delete(ClientResponse.class);
		
		int actual=response.getStatus();
		assertEquals(201,actual);
			
		}
    }
