package com.example.AmazonClone.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.AmazonClone.Entity.User;
import com.example.AmazonClone.Payload.LoginRequest;
import com.example.AmazonClone.Repository.UserRepository;
import com.example.AmazonClone.Security.JwtService;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public User getUserFromRequest(HttpServletRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null) {
            throw new RuntimeException("User not authenticated");
        }

        Long userId = Long.parseLong(authentication.getName());

        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    
    
    public boolean checkUser(String contact) {
        return userRepository.existsByEmailOrPhone(contact, contact);
    }

    
    // 🔎 Check if user exists by email or phone
    public boolean userExists(String contact) {
        if (contact.contains("@")) {
            return userRepository.existsByEmail(contact);
        } else {
            return userRepository.existsByPhone(contact);
        }
    }

    // 📝 Register user with hashed password
    public void registerUser(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }

    // 🔐 Authenticate login (email or phone) and generate JWT
    public String authenticate(LoginRequest request) {

        // Find user by email or phone
        User user = userRepository
                .findByEmailOrPhone(request.getContact(), request.getContact())
                .orElse(null);

        if (user == null) {
            return null; // user not found
        }

        // Check password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return null; // invalid password
        }

        // Generate JWT token
        return jwtService.generateToken(user);
    }
}