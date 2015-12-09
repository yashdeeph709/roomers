package com.roommanagement;

import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
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
	
	@Test
	public void setup(){
		Client client=Client.create();
		WebResource webResource=client.resource("http://localhost:8080/RoomManagement/delete/5668049163c75a0e77cb9fdf");
		ClientResponse response = webResource.type("application/json").get(ClientResponse.class);
		String output=response.getEntity(String.class);
		System.out.println(output);
		assertEquals(output,"{\"status\":\"success\",\"message\":\"User Deleted Successfully!\"}");
			
		}	
    }
