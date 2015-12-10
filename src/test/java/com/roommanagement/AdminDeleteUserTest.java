package com.roommanagement;

import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import com.roommanagement.beans.UserBean;
import com.roommanagement.services.UserService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


public class AdminDeleteUserTest {
	UserBean userBean;
	UserService userService;
	JSONObject obj = new JSONObject();
	Client client ;
	WebResource webResource; 
	String baseURI;
	
	@Before
	public void setup()
	{
		client = Client.create();
		 baseURI = "http://localhost:8080/RoomManagement/delete";
		
	}
	
	
	@Test
	public void deleteUserTest(){
		webResource = client.resource(baseURI+"/566804b063c75a0e77cb9fe0");
		ClientResponse response = webResource.type("application/json").get(ClientResponse.class);
		String output=response.getEntity(String.class);
		System.out.println(output);
		assertEquals(output,"{\"status\":\"success\",\"message\":\"User Deleted Successfully!\"}");
			
		}	
	
	@Test
	public void deleteUserWithoutIdTest(){
		webResource = client.resource(baseURI);
		ClientResponse response = webResource.type("application/json").get(ClientResponse.class);
		String output=response.getEntity(String.class);
		assertEquals(output,"{\"status\":\"failed\",\"message\":\"User Deleted Successfully!\"}");
			
		}
    }
