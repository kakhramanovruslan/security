package com.projects.security.controllers;

import com.projects.security.DTO.AuthDTO;
import com.projects.security.entity.User;
import com.projects.security.security.JWTUtil;
import com.projects.security.services.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RegistrationService registrationService;
    private final AuthenticationManager authenticationManager;

    private final JWTUtil jwtUtil;

    @PostMapping("/registration")
    // todo - cover response with ResponseEntity
    public Map<String, String> registration(@RequestBody User user){
        registrationService.register(user);
        String token = jwtUtil.generateToken(user.getUsername());
        return Map.of("token", token);
    }

    @PostMapping("/login")
    // todo - cover response with ResponseEntity
    public Map<String, String> login(@RequestBody AuthDTO authDTO){
        UsernamePasswordAuthenticationToken authInputToken
                = new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword());
        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException e){
            return Map.of("message", "Incorrect credentials!");
        }

        String token = jwtUtil.generateToken(authDTO.getUsername());
        return Map.of("token", token);
    }
}
