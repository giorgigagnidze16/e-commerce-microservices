package com.ecom.microservice.repository;

import java.util.Optional;

import com.ecom.microservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
