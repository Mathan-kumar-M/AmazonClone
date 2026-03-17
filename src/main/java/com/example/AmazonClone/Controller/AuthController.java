package com.example.AmazonClone.Controller;

import com.example.AmazonClone.Entity.User;
import com.example.AmazonClone.Payload.LoginRequest;
import com.example.AmazonClone.Payload.AuthResponse;
import com.example.AmazonClone.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserService userService;

    // Register new user
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {

        String contact = user.getEmail() != null ? user.getEmail() : user.getPhone();

        if (userService.userExists(contact)) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("User already exists");
        }

        userService.registerUser(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("User registered successfully");
    }
    
    @GetMapping("/check")
    public boolean check(@RequestParam String contact) {
        return userService.checkUser(contact);
    }

    // Login (email or phone)
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

        String token = userService.authenticate(request);

        if (token == null) {
            throw new RuntimeException("Invalid credentials");
        }

        AuthResponse response = new AuthResponse(token, "Login successful");

        return ResponseEntity.ok(response);
    }
}