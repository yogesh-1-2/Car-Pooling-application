package com.example.car_pooling.Manager;

import com.example.car_pooling.Entities.Trip;
import com.example.car_pooling.Repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TripManager {

    @Autowired
    private TripRepository tripRepository;

    public void createOrModifyTrip(Trip trip) {
        tripRepository.upsertTrip(trip);
    }

    public Trip getTripById(Integer tripId) {
        return tripRepository.getTripById(tripId);
    }

    public List<Trip> getAllTripByUserId(Integer userId) {
        return tripRepository.getAllTripByUserId(userId);
    }
}
