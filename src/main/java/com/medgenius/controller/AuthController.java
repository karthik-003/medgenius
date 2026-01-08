package com.medgenius.controller;

import com.medgenius.dto.request.LoginRequest;
import com.medgenius.dto.response.LoginResponse;
import com.medgenius.entity.User;
import com.medgenius.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest request) {

        try{
            User user = userRepository
                    .findByUsernameAndPasswordAndActiveTrue(
                            request.username,
                            request.password
                    )
                    .orElseThrow(() ->
                            new RuntimeException("Invalid credentials"));
            return ResponseEntity.ok(
                    new LoginResponse(
                            user.getId(),
                            user.getPharmacyId(),
                            user.getRole()
                    )
            );
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }
    }
}
