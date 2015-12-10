package com.roommanagement;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roommanagement.beans.Status;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RoomDeleteTest {
	Client client;
	WebResource webResource;
	String baseURI = "http://localhost:8080/RoomManagement";
	ClientResponse response;
	Status status = null;

	@Before
	public void setup() {
		client = Client.create();
		webResource = client.resource("http://localhost:8080/RoomManagement/getAdmin");
		response = webResource.accept("application/json").get(ClientResponse.class);
		String output = response.getEntity(String.class);
		ObjectMapper mapper = new ObjectMapper();
		try {
			status = mapper.readValue(output, Status.class);
			System.out.println(status);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testdeleteRoom() {

		client = Client.create();
		webResource = client.resource("http://localhost:8080/RoomManagement/deleteRoom/Bahar");
		ClientResponse response = webResource.accept("application/json").header("authToken", status.getMessage())
				.get(ClientResponse.class);
		String output = response.getEntity(String.class);
		String expected = "{\"status\":\"success\",\"message\":\"Room Deleted Successfully!\",\"dataOne\":null,\"data\":null}";
		System.out.println(output);
		assertEquals(output, expected);

	}

}