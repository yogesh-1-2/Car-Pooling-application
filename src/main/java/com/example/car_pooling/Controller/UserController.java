package com.example.car_pooling.Controller;

import com.example.car_pooling.Entities.User;
import com.example.car_pooling.Service.UserService;
import com.example.car_pooling.Validations.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private  UserValidation userValidation;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userValidation.validateUser(user);
        userService.registerUser(user);
        return new ResponseEntity<>("User Registered", HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
}
