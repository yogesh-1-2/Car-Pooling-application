package com.example.car_pooling.Validations;

import com.example.car_pooling.Entities.User;
import com.example.car_pooling.Exceptions.UserExceptions;
import com.example.car_pooling.Util.Constants;
import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserValidation {
    public void validateUser(User user) {
        if (Objects.isNull(user.getName())) {
            throw new UserExceptions.UserFieldNotFoundException(Constants.NAME);
        }

        if (Objects.isNull(user.getEmailId())) {
            throw new UserExceptions.UserFieldNotFoundException(Constants.EMAIL);
        }

        if (Objects.isNull(user.getPassword())) {
            throw new UserExceptions.UserFieldNotFoundException(Constants.PASSWORD);
        }

        if (Objects.isNull(user.getPhoneNumber())) {
            throw new UserExceptions.UserFieldNotFoundException(Constants.PHONE_NUMBER);
        }
    }
}
