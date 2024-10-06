package com.example.car_pooling.Exceptions;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BookingExceptions {
    private static final String BOOKING_NOT_FOUND = "Booking not found";
    private static final String INVALID_BOOKING_USER = "Invalid booking user";
    private static final String SEATS_NOT_AVAILABLE = "Seats not available";


    public static class BookingNotFoundException extends RuntimeException {
        public BookingNotFoundException() {
            super(BOOKING_NOT_FOUND);
        }
    }

    public static class InvalidBookingUserException extends RuntimeException {
        public InvalidBookingUserException() {
            super(INVALID_BOOKING_USER);
        }
    }

    public static class SeatsNotAvailableException extends RuntimeException {
        public SeatsNotAvailableException() {
            super(SEATS_NOT_AVAILABLE);
        }
    }
}
