package com.roommanagement;

import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roommanagement.beans.Status;
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
			System.out.println("Initial "+status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		
	}
	
	
	@Test
	public void deleteUserTest(){
		
		webResource = client.resource(baseURI+"/delete/56685de116697f79e253431f");
		ClientResponse response = webResource.accept("application/json").header("authToken", status.getMessage()).get(ClientResponse.class);
		String output=response.getEntity(String.class);
		System.out.println(output);
		assertEquals("{\"status\":\"success\",\"message\":\"User Deleted Successfully!\",\"dataOne\":null,\"data\":null}",output);
			
		}	
	
	@Test
	public void deleteUserWithoutIdTest(){
		webResource = client.resource(baseURI+"/delete");
		ClientResponse response = webResource.accept("application/json").header("authToken", status.getMessage()).get(ClientResponse.class);
		String output=response.getEntity(String.class);
		assertEquals("{\"status\":\"failed\",\"message\":\"User Deleted Successfully!\",\"dataOne\":null,\"data\":null}",output);
			
		}
    }
