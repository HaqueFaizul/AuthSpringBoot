package com.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.dto.LoginRequest;
import com.auth.dto.LoginResponse;
import com.auth.service.jwt.CustomerService;
import com.auth.utils.JwtUtil;

@RestController
@RequestMapping("/login")
@CrossOrigin("*")
public class LoginController {
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtUtil;
	@PostMapping
	public ResponseEntity<?>login(@RequestBody LoginRequest loginRequest)
	{
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()
							));
		}
		catch(AuthenticationException e)
		{
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
		UserDetails userDetails;
		try {
			userDetails=customerService.loadUserByUsername(loginRequest.getEmail());
		}
		catch(UsernameNotFoundException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		String jwt=jwtUtil.generateToken(userDetails.getUsername());
		return ResponseEntity.ok(new LoginResponse(jwt));
	}

}
