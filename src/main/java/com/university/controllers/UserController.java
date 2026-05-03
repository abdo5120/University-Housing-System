package com.university.controllers;

import com.university.dtos.response.UserDto;
import com.university.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers(
            @RequestParam(required = false) String email) {
        logger.info("GET /api/users — email filter: {}", email);

        List<UserDto> result = (email != null && !email.isBlank())
                ? List.of(userService.getUserByEmail(email))
                : userService.getAllUsers();

        return ResponseEntity.ok(result);
    }
}
