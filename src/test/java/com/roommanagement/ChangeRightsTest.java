package com.roommanagement;


import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roommanagement.beans.Bookings;
import com.roommanagement.collections.RoomCollection;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ChangeRightsTest {

	static Client client;
	String baseURI = "http://localhost:8080/roommanagement/rights";
	WebResource webResource;
	ClientResponse response;
	
	@BeforeClass
	public static void setUp() throws Exception {
		
		client=Client.create();
		
	}
	
	/************testChangeRights**********************/
	@Test
	public void testChangeRights() throws JsonParseException, JsonMappingException, IOException {		

		webResource=client.resource(baseURI+"/56685de116697f79e253431f");
		ClientResponse response = webResource.type("application/json").header("authToken","56685db316697f79e253431d").put(ClientResponse.class,"");
		assertEquals(202,response.getStatus());
	}
	
	/************testChangeRightsInvalidId**********************/
	@Test
	public void testChangeRightsInvalidId() throws JsonParseException, JsonMappingException, IOException {		

		webResource=client.resource(baseURI+"/56685d");
		ClientResponse response = webResource.type("application/json").header("authToken","56685db316697f79e253431d").put(ClientResponse.class,"");
		assertEquals(400,response.getStatus());
	}
	

	
	
}
