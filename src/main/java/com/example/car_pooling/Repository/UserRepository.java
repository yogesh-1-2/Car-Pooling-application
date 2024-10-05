package com.example.car_pooling.Repository;

import com.example.car_pooling.Entities.User;
import com.example.car_pooling.Exceptions.UserExceptions;
import lombok.Data;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UserRepository {
    private List<User> users;
    private int        nextId;

    public UserRepository() {
        this.users = new ArrayList<>();
        nextId = 1;
    }

    public void addUser(User user) {
        if (Objects.isNull(user.getId())) {
            user.setId(nextId++);
        } else {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getId().equals(user.getId())) {
                    users.set(i, user); // Update the element in the list
                }
            }
        }
        users.add(user);
    }

    public void removeUser(User user){
        users.remove(user);
    }

    public List<User> getUsers(){
        return users;
    }

    public User getUserById(int id){
        for(User user: users){
            if (user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public User getUserByEmailId(String email) {
        for (User user: users) {
            if (user.getEmailId().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }
}
