package com.roommanagement;

import static org.junit.Assert.assertEquals;

import java.net.URISyntaxException;
import org.junit.Test;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


public class CreateUserTest {


	@Test
	public void testCreateUser() throws URISyntaxException {
		Client client = Client.create();
		WebResource webResource = client.resource("http://localhost:8080/register");
		String expected = "{\"name\":\"Palakh\",\"email_id\":\"palakh@gmail.com\",\"password\":\"Palakh\",\"rights\":\"1\"}";

		ClientResponse response = webResource.type("application/json").post(ClientResponse.class, expected);
		String actual=response.getEntity(String.class);
		System.out.println(actual);
		assertEquals(actual, "{\"id\":null,\"name\":\"Palakh\",\"email\":null}");

	}

}
