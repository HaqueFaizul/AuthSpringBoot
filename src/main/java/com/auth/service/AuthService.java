package com.auth.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.dto.SignupRequest;
import com.auth.entity.Customer1;
import com.auth.repository.CustomerRepository;
@Service
public class AuthService {
	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public boolean createCustomer(SignupRequest signupRequest) {
		
		if(customerRepo.existsByEmail(signupRequest.getEmail()))
		{
			return false;
		}
		Customer1 customer=new Customer1();
		BeanUtils.copyProperties(signupRequest, customer);
		
		//Hashing the password
		String hashPassword=passwordEncoder.encode(signupRequest.getPassword());
		customer.setPassword(hashPassword);
		customerRepo.save(customer);
		
		return true;
	}
	
	

}
