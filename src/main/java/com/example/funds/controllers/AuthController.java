package com.example.funds.controllers;



import com.example.funds.dto.LoginDto;
import com.example.funds.dto.UserDto;
import com.example.funds.entities.User;
import com.example.funds.payload.LoginResponse;

import com.example.funds.services.implementation.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/auth")
public class AuthController {



    private final UserServiceImpl userService;

    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.signUp(userDto), HttpStatus.OK);
    }


    @PostMapping("/signin")
    public ResponseEntity<LoginResponse> authenticateUser(HttpSession session, @RequestBody LoginDto loginDto){
        User user = userService.findByEmail(loginDto.getEmail());
        if(user.getPassword().equals(loginDto.getPassword())){
//            session.setAttribute("id",user.getId());
            return ResponseEntity.ok(new LoginResponse(user.getId(), user.getEmail(),user.getRole().toString()));
        }
        return new ResponseEntity<>(new LoginResponse("user not allowed"),HttpStatus.BAD_REQUEST);
    }

//    public ResponseEntity<?> logOut(HttpSession session){
//        session.invalidate();
//        return new ResponseEntity<>(HttpStatus.OK);
//    }




}
