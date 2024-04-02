package com.projects.security.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class SomeAppController {

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello world!");
    }
}
