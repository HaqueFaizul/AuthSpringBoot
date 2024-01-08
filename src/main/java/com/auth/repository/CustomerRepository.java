package com.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth.entity.Customer1;
@Repository
public interface CustomerRepository extends JpaRepository<Customer1, Integer>{

	boolean existsByEmail(String email);

	Optional<Customer1> findByEmail(String email);

}
