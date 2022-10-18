package com.example.demo.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class AuthorizationFilter extends BasicAuthenticationFilter{
	
	public AuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		
		
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req,
			                        HttpServletResponse res,
			                        FilterChain chain) throws IOException, ServletException {
		
		
		
		String header = req.getHeader(SecurityConstants.HEADER_STRING);
		
		if(header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			
			chain.doFilter(req, res);
			
			return;
		}
		
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
		
	}

	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) throws UnsupportedEncodingException {
		
		String token = request.getHeader(SecurityConstants.HEADER_STRING);
		
		if(token != null) {
			
			token = token.replace(SecurityConstants.TOKEN_PREFIX, "");
		
		
		
			SecretKeySpec secret_key = new SecretKeySpec(SecurityConstants.getTokenSecret().getBytes("UTF-8"), "HmacSHA256");
			      
			
			
			String user = Jwts.builder()
			.setExpiration(new Date(System.currentTimeMillis()+ SecurityConstants.EXPIRATION_TIME))
			.signWith(secret_key)
			.compact();
			
			
				if(user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}
			
			return null;
		}
		
		
		
		return null;
		
		
	}

}


