package com.example.car_pooling.Exceptions;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserExceptions {

    private static final String USER_FIELD_NOT_FOUND = "User %s information not provided";
    private static final String USER_ALREADY_EXISTS = "User with email %s already exists";
    private static final String INVALID_USER = "Invalid User";

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

    public static class InvalidUser extends RuntimeException {
        public InvalidUser() {
            super(String.format(INVALID_USER));
        }
    }
}