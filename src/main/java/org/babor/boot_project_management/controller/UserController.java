package org.babor.boot_project_management.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.babor.boot_project_management.dto.user.UserRequestDto;
import org.babor.boot_project_management.service.UserService;
import org.babor.boot_project_management.validatior.ProjectValidator;
import org.babor.boot_project_management.validatior.UserValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final UserValidator userValidator;

    @GetMapping
    public ResponseEntity<?> indexUser() {
        return new ResponseEntity<>("ok", HttpStatus.CREATED);
    }

    @PostMapping
    @Operation(
            summary = "Create User"
    )
    public ResponseEntity<?> create(@Valid @RequestBody UserRequestDto user) {
        userValidator.userCreationValidator(user);
        return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
    }

}
