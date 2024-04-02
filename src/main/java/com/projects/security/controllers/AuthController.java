package com.projects.security.controllers;

import com.projects.security.dto.AuthDto;
import com.projects.security.entity.User;
import com.projects.security.security.JwtUtil;
import com.projects.security.services.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RegistrationService registrationService;
    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    @PostMapping("/registration")
    public ResponseEntity<Map<String, String>> registration(@RequestBody User user){
        registrationService.register(user);
        String token = jwtUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthDto authDTO){
        UsernamePasswordAuthenticationToken authInputToken
                = new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword());
        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException e){
            return ResponseEntity.ok(Map.of("message", "Incorrect credentials!"));
        }

        String token = jwtUtil.generateToken(authDTO.getUsername());
        return ResponseEntity.ok(Map.of("token", token));
    }
}
