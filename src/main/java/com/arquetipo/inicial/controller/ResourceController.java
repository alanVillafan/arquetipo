package com.arquetipo.inicial.controller;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resource")
@Slf4j
public class ResourceController {

    @GetMapping("/admin")
    ResponseEntity<?> admin(@AuthenticationPrincipal User user) {
        log.info(user.getUsername());
        return new ResponseEntity<>("Hola admin", HttpStatus.OK);
    }
}
