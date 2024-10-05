package com.example.car_pooling.Exceptions;

import lombok.experimental.UtilityClass;

@UtilityClass
public class VehicleExceptions {
    private final String VEHICLE_FIELD_NOT_FOUND = "Vehicle field %s not found";
    private final String VEHICLE_ATTRIBUTE_NOT_FOUND = "Vehicle attribute not found";
    private final String DUPLICATE_VEHICLE = "Vehicle with number %s already exists";
    private final String VEHICLE_NOT_PROVIDED = "empty vehicle field";
    private final String VEHICLE_ALREADY_OWNED_BY_SOME_OTHER_USER = "vehicle is already owned by someone else";

    public static class VehicleNotFoundException extends RuntimeException {
        public VehicleNotFoundException() {
            super(VEHICLE_NOT_PROVIDED);
        }
    }

    public static class VehicleFieldNotFoundException extends RuntimeException {
        public VehicleFieldNotFoundException(String field) {
            super(String.format(VEHICLE_FIELD_NOT_FOUND, field));
        }
    }

    public static class VehicleAttributesNotFoundException extends RuntimeException {
        public VehicleAttributesNotFoundException() {
            super(String.format(VEHICLE_ATTRIBUTE_NOT_FOUND));
        }
    }

    public static class VehicleAlreadyOwnedException extends RuntimeException {
        public VehicleAlreadyOwnedException() {
            super(VEHICLE_ALREADY_OWNED_BY_SOME_OTHER_USER);
        }
    }

    public static class DuplicateVehicleException extends RuntimeException {
        public DuplicateVehicleException(String vehicleNumber) {
            super(String.format(DUPLICATE_VEHICLE, vehicleNumber));
        }
    }
}
