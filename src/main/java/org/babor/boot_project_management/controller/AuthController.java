package org.babor.boot_project_management.controller;


import lombok.RequiredArgsConstructor;
import org.babor.boot_project_management.service.UserService;
import org.babor.boot_project_management.utils.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<String> greet() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String token = JwtUtils.generateToken(username);
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }

}
