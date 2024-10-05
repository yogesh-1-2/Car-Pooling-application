package com.example.car_pooling.Manager;

import com.example.car_pooling.Entities.User;
import com.example.car_pooling.Exceptions.UserExceptions;
import com.example.car_pooling.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class UserManager {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(User user) {
        if (Objects.nonNull(userRepository.getUserByEmailId(user.getEmailId()))) {
            throw new UserExceptions.DuplicateUserException(user.getEmailId());
        }

        userRepository.addUser(user);
    }

    public List<User> getAllUsers() {
        return userRepository.getUsers();
    }
}
