package com.example.car_pooling.Service;

import com.example.car_pooling.Entities.User;
import com.example.car_pooling.Manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserManager userManager;

    public void registerUser(User user) {
        userManager.registerUser(user);
    }

    public List<User> getAllUsers() {
        return userManager.getAllUsers();
    }
}
