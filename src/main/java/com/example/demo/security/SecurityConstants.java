package com.example.demo.security;

import com.example.demo.SpringApplicationContext;

public class SecurityConstants {
	
	public static final long EXPIRATION_TIME = 864000000; //10 days
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SING_UP_URL = "/users";

	public static String getTokenSecret() {
		
		AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties"); // we had to put name on Bean on mobileWsApp class because we give different name here!
		return appProperties.getTokenSecret();
	}
}
