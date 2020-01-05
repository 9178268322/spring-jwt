package com.wbl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wbl.models.AuthenticationRequest;
import com.wbl.models.AuthenticationResponse;
import com.wbl.models.UserInfo;
import com.wbl.service.MyUserDetailsService;
import com.wbl.util.JwtUtil;

@RestController
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager; 
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@RequestMapping({"/hello"})
	public String hello() {
		return "Hello World !!";
	}
	
	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	public UserInfo addUser(@RequestBody UserInfo user) {
		return myUserDetailsService.addUser(user);
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
				);
		} catch (BadCredentialsException e) {
			 throw new Exception("Incorrect username and password", e);
		}
		
		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		
		final String jwt = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
