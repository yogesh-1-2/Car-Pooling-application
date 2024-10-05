package com.example.car_pooling.Service;

import com.example.car_pooling.Entities.Address;
import com.example.car_pooling.Entities.Trip;
import com.example.car_pooling.Exceptions.TripExceptions;
import com.example.car_pooling.Manager.TripManager;
import com.example.car_pooling.Util.Constants;
import com.example.car_pooling.Validations.TripValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
public class TripService {

    @Autowired
    private TripManager tripManager;

    @Autowired
    private TripValidation tripValidation;

    public void createTrip(Trip trip, Integer ownerId) {
        tripValidation.validateTrip(trip, ownerId);
        trip.setOwnerId(ownerId);
        Address origin = trip.getOrigin();
        Address destination = trip.getDestination();
        origin.setStateCode(getStateCode(origin.getState().trim().toLowerCase()));
        destination.setStateCode(getStateCode(destination.getState().trim().toLowerCase()));
        tripManager.createOrModifyTrip(trip);
    }

    public void endTrip(Integer tripId, Integer ownerId) {
        tripValidation.validateTripAccess(tripId, ownerId);
        Trip trip = tripManager.getTripById(tripId);
        if (Objects.isNull(trip)) {
            throw new TripExceptions.TripNotFoundException();
        }
        long epochSeconds = Instant.now().getEpochSecond();
        trip.setEndTime(epochSeconds);
        tripManager.createOrModifyTrip(trip);
    }

    public List<Trip> getAllTripByUserId(Integer userId) {
        return tripManager.getAllTripByUserId(userId);
    }

    private int getStateCode(String state) {
        return Constants.stateCodeMap.get(state);
    }
}
