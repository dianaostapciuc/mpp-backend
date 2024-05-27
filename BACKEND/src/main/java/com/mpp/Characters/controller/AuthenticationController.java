package com.mpp.Characters.controller;

import com.mpp.Characters.model.ApplicationUser;
import com.mpp.Characters.DTO.LoginDTO;
import com.mpp.Characters.DTO.LoginResponseDTO;
import com.mpp.Characters.DTO.RegistrationDTO;
import com.mpp.Characters.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationDTO body){
        return authenticationService.registerUser(body.getUsername(), body.getPassword(), body.getEmail());
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody LoginDTO body){
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        authenticationService.logoutUser(token);
        return ResponseEntity.ok().build();
    }
}
