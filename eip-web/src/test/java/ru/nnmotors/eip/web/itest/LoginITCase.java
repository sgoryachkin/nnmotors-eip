package ru.nnmotors.eip.web.itest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

public class LoginITCase {
	
	public static final String LOGIN_FORM = "action=\"login\" method=\"post\"";
	
	
	@Test
	public void myTest() {
		
		  String loginPage = new RestTemplate()
		                          .getForObject(
		                          "http://localhost:8080/jetty-dev/login",
		                          String.class);
		  
		  assertTrue(loginPage.contains(LOGIN_FORM));
		
	}

}
