package com.auth.service.jwt;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth.entity.Customer1;
import com.auth.repository.CustomerRepository;

@Service
public class CustomerService implements UserDetailsService {
	@Autowired
	private CustomerRepository  customerRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Customer1 customer= customerRepo.findByEmail(email)
				.orElseThrow(()->new UsernameNotFoundException("Customer not found with "+email));
				
		return new User(customer.getEmail(),customer.getPassword(),Collections.emptyList());
	}
}
