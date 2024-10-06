package com.example.car_pooling.Service;

import com.example.car_pooling.DTO.TripFilteringRequestDto;
import com.example.car_pooling.Entities.Address;
import com.example.car_pooling.Entities.Enums.TripSelectionEnums;
import com.example.car_pooling.Entities.Trip;
import com.example.car_pooling.Exceptions.TripExceptions;
import com.example.car_pooling.Manager.TripManager;
import com.example.car_pooling.Service.Strategy.StrategyInterfaces.TripFilteringStrategy;
import com.example.car_pooling.Service.Strategy.TripFilteringByVacancyStrategy;
import com.example.car_pooling.Service.Strategy.TripFilteringByVehicleStrategy;
import com.example.car_pooling.Util.Constants;
import com.example.car_pooling.Validations.TripValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    private TripFilteringByVacancyStrategy tripFilteringByVacancyStrategy;

    @Autowired
    private TripFilteringByVehicleStrategy tripFilteringByVehicleStrategy;

    public void createTrip(Trip trip, Integer ownerId) {
        trip.setOwnerId(ownerId);
        Address origin = trip.getOrigin();
        Address destination = trip.getDestination();
        origin.setStateCode(getStateCode(origin.getState().trim().toLowerCase()));
        destination.setStateCode(getStateCode(destination.getState().trim().toLowerCase()));
        tripValidation.validateTrip(trip, ownerId);
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

    public List<Trip> searchTrip(Integer originStateCode, Integer destinationStateCode, TripSelectionEnums tripSelectionStrategy, String vehicleType, Integer seats) {
        //validation to be included
        TripFilteringStrategy tripFilteringStrategy = tripFilteringByVacancyStrategy;
        String requiredVehicleType = "";
        if (tripSelectionStrategy.equals(TripSelectionEnums.PREFERRED_VEHICLE)) {
            tripFilteringStrategy = tripFilteringByVehicleStrategy;
            requiredVehicleType = vehicleType;
        }

        TripFilteringRequestDto
                tripFilterRequest = TripFilteringRequestDto.builder()
                .originStateCode(originStateCode)
                .destinationStateCode(destinationStateCode)
                .seatsAvailable(seats)
                .vehicleType(requiredVehicleType)
                .build();
        return tripFilteringStrategy.filterTrips(tripFilterRequest);
    }

    private int getStateCode(String state) {
        return Constants.stateCodeMap.get(state);
    }
}
