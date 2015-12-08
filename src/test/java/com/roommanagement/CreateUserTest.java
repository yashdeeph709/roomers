package com.roommanagement;

import static org.junit.Assert.assertEquals;

import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;

import org.json.JSONException;
import org.json.JSONObject;

import com.roommanagement.collections.UserCollection;
import com.roommanagement.services.UserService;
import com.roommanagement.services.UserServiceImpl;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.research.ws.wadl.Response;

public class CreateUserTest {

	UserCollection userCollection;
	UserService userService;
	// new UserBean(registration.getName(), registration.getEmail_id(),
	// registration.getPassword(),registration.getRights()));

	@Before
	public void setUp() {
		userService = new UserServiceImpl();
		userCollection=new UserCollection();
		userCollection.setName("Palakh");
		userCollection.setEmail("palakh.7@gmail.com");
		userCollection.setPassword("password");
		userCollection.setRights("1");
	}

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
