package com.example.funds.controllers;

import com.example.funds.dto.UserDto;
import com.example.funds.entities.User;
import com.example.funds.services.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable(name = "id")Long id, UserDto userDto){
        return ResponseEntity.ok(userService.updateProfile(id,userDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable(name = "id")Long id){
        return ResponseEntity.ok(userService.getUser(id));
    }

}
