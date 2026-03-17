package com.example.AmazonClone.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AmazonClone.Entity.User;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by email
    Optional<User> findByEmail(String email);

    // Find user by phone
    Optional<User> findByPhone(String phone);

    // Find user by email OR phone
    Optional<User> findByEmailOrPhone(String email, String phone);

    // Check existence
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    
    boolean existsByEmailOrPhone(String email, String phone);
}