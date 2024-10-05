package com.example.car_pooling.Exceptions;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserExceptions {

    private static final String USER_FIELD_NOT_FOUND = "User %s information not provided";
    private static final String USER_ALREADY_EXISTS = "User with email %s already exists";

    public static class UserFieldNotFoundException extends RuntimeException {
        public UserFieldNotFoundException(String field) {
            super(String.format(USER_FIELD_NOT_FOUND, field));
        }
    }

    public static class DuplicateUserException extends RuntimeException {
        public DuplicateUserException(String email) {
            super(String.format(USER_ALREADY_EXISTS, email));
        }
    }
}