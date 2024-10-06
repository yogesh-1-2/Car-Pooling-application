package com.example.car_pooling.Exceptions;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TripExceptions {
    private final String TRIP_FIELD_NOT_FOUND = "Trip detail : %s not found";
    private final String TRIP_NOT_FOUND = "Trip not found";
    private final String TRIP_VEHICLE_NOT_EXIST = "Trip vehicle number : %s doesn't belong to user";
    private final String TRIP_WRONG_ACCESS = "Access denied";
    private final String TRIP_INVALID_STATE_FOUND = "Trip invalid state: %s found";
    private final String TRIP_VEHICLE_ALREADY_IN_USE = "Trip vehicle %s already in user";

    public static class TripNotFoundException extends RuntimeException {
        public TripNotFoundException() {
            super(TRIP_NOT_FOUND);
        }
    }

    public static class TripFieldNotFoundException extends RuntimeException {
        public TripFieldNotFoundException(String field) {
            super(String.format(TRIP_FIELD_NOT_FOUND, field));
        }
    }

    public static class TripInValidStateFoundException extends RuntimeException {
        public TripInValidStateFoundException(String field) {
            super(String.format(TRIP_INVALID_STATE_FOUND, field));
        }
    }

    public static class TripAccessDeniedException extends RuntimeException {
        public TripAccessDeniedException() {
            super(String.format(TRIP_WRONG_ACCESS));
        }
    }

    public static class TripWrongVehicle extends RuntimeException {
        public TripWrongVehicle(String state) {
            super(String.format(TRIP_VEHICLE_NOT_EXIST, state));
        }
    }

    public static class TripVehicleAlreadyInUse extends RuntimeException {
        public TripVehicleAlreadyInUse(String vehicleNumber) {
            super(String.format(TRIP_VEHICLE_ALREADY_IN_USE, vehicleNumber));
        }
    }

}
