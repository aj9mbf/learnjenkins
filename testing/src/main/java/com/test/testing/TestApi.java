package com.test.testing;

import java.io.IOException;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestApi {

	@Test(priority=0)
	public void postingUser() {
		HttpClient client=HttpClientBuilder.create().build();
		HttpPost request=new HttpPost("http://localhost:9090/futurewise-api-1.0-SNAPSHOT/users");
		try {
			request.setHeader("Content-Type","application/json; charset=UTF-8");
			request.setEntity(new StringEntity("{\n" + 
					"       \"id\": 44,\n" + 
					"       \"name\": \"Dhrumil\",\n" + 
					"       \"password\" : \"12345\",\n" + 
					"       \"role\":\"user\"\n" + 
					"   }"));
		} catch (Exception e) {
			Assert.fail("Unable to hit /users");
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testGetUser() {
		HttpClient client=HttpClientBuilder.create().build();
		int id= new Random().nextInt(44);
		System.out.println(id);
		HttpGet request=new HttpGet("http://localhost:9090/futurewise-api-1.0-SNAPSHOT/user/44");
		try {
			HttpResponse response=client.execute(request);
			Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
			Assert.assertEquals("application/json", ContentType.getOrDefault(response.getEntity()).getMimeType());
			Assert.assertEquals(31,retrieveResourceFromResponse(response, User.class).getId() );
		} catch (Exception e) {
			Assert.fail("Unable to hit /users");
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testGetUsers() {
		HttpClient client=HttpClientBuilder.create().build();
		HttpGet request=new HttpGet("http://localhost:9090/futurewise-api-1.0-SNAPSHOT/users");
		try {
			HttpResponse response=client.execute(request);
			Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
			Assert.assertEquals("application/json", ContentType.getOrDefault(response.getEntity()).getMimeType());
		} catch (Exception e) {
			Assert.fail("Unable to hit /users");
			e.printStackTrace();
		}	
		
	}
	
	public static <T> T retrieveResourceFromResponse(HttpResponse response, Class<T> clazz) 
			  throws IOException {
			  
			    String jsonFromResponse = EntityUtils.toString(response.getEntity());
			    ObjectMapper mapper = new ObjectMapper();
			    return mapper.readValue(jsonFromResponse, clazz);
			}
	
}
