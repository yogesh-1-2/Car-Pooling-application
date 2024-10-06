package com.example.car_pooling.Service.Strategy;

import com.example.car_pooling.DTO.TripFilteringRequestDto;
import com.example.car_pooling.Entities.Trip;
import com.example.car_pooling.Manager.TripManager;
import com.example.car_pooling.Service.Strategy.StrategyInterfaces.RoutingStrategy;
import com.example.car_pooling.Service.Strategy.StrategyInterfaces.TripFilteringStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TripFilteringByVacancyStrategy implements TripFilteringStrategy {

    @Autowired
    private MutiRouteStrategy multiRouteStrategy;

    @Override
    public List<Trip> filterTrips(TripFilteringRequestDto tripFilterRequest) {
        RoutingStrategy routingStrategy = multiRouteStrategy;
        List<List<Trip>> trips = routingStrategy.getRoute(
                tripFilterRequest.getOriginStateCode(),
                tripFilterRequest.getDestinationStateCode(),
                tripFilterRequest.getSeatsAvailable());
        if (trips.isEmpty()) {
            return Collections.emptyList();
        }
        List<Trip> mostVacantTrip = trips.get(0);
        for (List<Trip> tripList : trips) {
            if (tripList.get(0).getAvailableSeats() > mostVacantTrip.get(0).getAvailableSeats()) {
                mostVacantTrip = tripList;
            }
        }
        return mostVacantTrip;
    }
}
