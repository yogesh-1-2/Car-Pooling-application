package com.example.car_pooling.Validations;

import com.example.car_pooling.Entities.Address;
import com.example.car_pooling.Entities.Trip;
import com.example.car_pooling.Exceptions.TripExceptions;
import com.example.car_pooling.Manager.TripManager;
import com.example.car_pooling.Manager.UserResourceManager;
import com.example.car_pooling.Util.Constants;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class TripValidation {

    @Autowired
    private UserResourceManager userResourceManager;

    @Autowired
    private TripManager tripManager;

    public void validateTrip(Integer tripId) {
        if (Objects.isNull(tripId)) {
            throw new TripExceptions.TripNotFoundException();
        }

        Trip trip = tripManager.getTripById(tripId);
        if (Objects.isNull(trip)) {
            throw new TripExceptions.TripNotFoundException();
        }
    }

    public void validateTrip(List<Integer> tripIds) {
        if (Objects.isNull(tripIds)) {
            throw new TripExceptions.TripNotFoundException();
        }

        tripIds.forEach(this::validateTrip);
    }

    public void validateTrip(Trip trip) {
        if (Objects.isNull(trip)) {
            throw new TripExceptions.TripNotFoundException();
        }

        validateAddress(trip.getOrigin());
        validateAddress(trip.getDestination());

        if (Objects.isNull(trip.getOwnerId())) {
            throw new TripExceptions.TripFieldNotFoundException(Constants.OWNER_ID);
        }

        if (Objects.isNull(trip.getAvailableSeats())) {
            throw new TripExceptions.TripFieldNotFoundException(Constants.SEATS_AVAILABLE);
        }

        if (Objects.isNull(trip.getVehicleNumber())) {
            throw new TripExceptions.TripFieldNotFoundException(Constants.VEHICLE_ID);
        }
    }

    public void validateTrip(Trip trip, Integer ownerId) {
        Integer userId = userResourceManager.getUserIdByAssetId(trip.getVehicleNumber());
        if (Boolean.FALSE.equals(ownerId.equals(userId))) {
            throw new TripExceptions.TripWrongVehicle(trip.getVehicleNumber());
        }

        List<Trip> trips = tripManager.getActiveTripsByOrignDest(trip.getOrigin().getStateCode(), trip.getDestination().getStateCode());
        for (Trip existingTrip : trips) {
            if (Objects.isNull(existingTrip.getEndTime()) &&
                    Objects.equals(existingTrip.getVehicleNumber(), trip.getVehicleNumber())) {
                    throw new TripExceptions.TripVehicleAlreadyInUse(trip.getVehicleNumber());
            }
        }
    }

    public void validateAddress(Address address) {
        if (Objects.isNull(address)) {
            throw new TripExceptions.TripFieldNotFoundException(Constants.ADDRESS);
        }
        if (Objects.isNull(address.getState()) ||
                Objects.isNull(Constants.stateCodeMap.get(address.getState().trim().toLowerCase()))) {
            throw new TripExceptions.TripInValidStateFoundException(address.getState());
        }
    }

    public void validateTripAccess(Integer tripId, Integer ownerId) {
        Trip trip = tripManager.getTripById(tripId);
        if (Objects.isNull(trip)) {
            throw new TripExceptions.TripNotFoundException();
        }
        if (Boolean.FALSE.equals(trip.getOwnerId().equals(ownerId))) {
            throw new TripExceptions.TripAccessDeniedException();
        }
    }

}
