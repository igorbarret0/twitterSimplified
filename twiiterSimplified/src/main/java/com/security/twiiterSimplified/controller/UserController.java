package com.security.twiiterSimplified.controller;

import com.security.twiiterSimplified.dtos.CreateUserDto;
import com.security.twiiterSimplified.entiities.User;
import com.security.twiiterSimplified.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Void> saveUser(@RequestBody CreateUserDto dto) {

        userService.saveUser(dto);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<User>> listUsers() {

        var response = userService.listUsers();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
