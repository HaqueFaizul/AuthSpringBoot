package com.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@GetMapping("/hi")
	public String HiCustomer()
	{
		return "Hey Customer welcome to my Authentication";
	}
}
