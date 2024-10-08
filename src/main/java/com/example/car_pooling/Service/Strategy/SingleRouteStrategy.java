package com.example.car_pooling.Service.Strategy;

import com.example.car_pooling.Entities.Trip;
import com.example.car_pooling.Manager.TripManager;
import com.example.car_pooling.Service.Strategy.StrategyInterfaces.RoutingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingleRouteStrategy implements RoutingStrategy {

    @Autowired
    private TripManager tripManager;

    @Override
    public List<List<Trip>> getRoute(Integer originStateCode, Integer destinationStateCode, Integer seatsAvailable) {
        List<Trip> trips = tripManager.getActiveTripsByOrignDest(originStateCode, destinationStateCode);
        return trips.stream()
                .filter(trip -> trip.getAvailableSeats() >= seatsAvailable)
                .map(List::of).toList();
    }
}
