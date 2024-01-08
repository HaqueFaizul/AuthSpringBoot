package com.auth.dto;

public class LoginResponse {
	public String jwtToken;
	
	public LoginResponse(String jwtToken)
	{
		this.jwtToken=jwtToken;
	}
}
