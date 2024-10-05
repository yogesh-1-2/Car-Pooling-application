package com.example.car_pooling.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserExceptions.DuplicateUserException.class)
    public ResponseEntity<Object> handleDuplicateUserException(UserExceptions.DuplicateUserException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT); // Return 409 Conflict with message
    }

    @ExceptionHandler(UserExceptions.UserFieldNotFoundException.class)
    public ResponseEntity<Object> handleUserFieldNotFoundException(UserExceptions.UserFieldNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND); // Return 404 Not Found with message
    }

    @ExceptionHandler(VehicleExceptions.VehicleNotFoundException.class)
    public ResponseEntity<Object> handleVehicleNotFoundException(VehicleExceptions.VehicleNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND); // Return 404 Not Found with message
    }

    @ExceptionHandler(VehicleExceptions.VehicleFieldNotFoundException.class)
    public ResponseEntity<Object> handleVehicleFieldNotFoundException(VehicleExceptions.VehicleFieldNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST); // Return 400 Bad Request with message
    }

    @ExceptionHandler(VehicleExceptions.DuplicateVehicleException.class)
    public ResponseEntity<Object> handleDuplicateVehicleException(VehicleExceptions.DuplicateVehicleException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT); // Return 409 Conflict with message
    }

    @ExceptionHandler(VehicleExceptions.VehicleAlreadyOwnedException.class)
    public ResponseEntity<Object> handleVehicleAlreadyOwnedBySomeoneElseException(VehicleExceptions.VehicleAlreadyOwnedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT); // Return 409 Conflict with message
    }

    @ExceptionHandler(VehicleExceptions.VehicleAttributesNotFoundException.class)
    public ResponseEntity<Object> handleVehicleAttributesNotFoundException(VehicleExceptions.VehicleAttributesNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND); // Return 404 Not Found with message
    }

    @ExceptionHandler(TripExceptions.TripNotFoundException.class)
    public ResponseEntity<Object> handleTripNotFoundException(TripExceptions.TripNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND); // Return 404 Not Found with message
    }

    @ExceptionHandler(TripExceptions.TripFieldNotFoundException.class)
    public ResponseEntity<Object> handleTripFieldNotFoundException(TripExceptions.TripFieldNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST); // Return 400 Bad Request with message
    }

    // Handle all other exceptions (optional)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalExceptions(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 Internal Server Error
    }
}

