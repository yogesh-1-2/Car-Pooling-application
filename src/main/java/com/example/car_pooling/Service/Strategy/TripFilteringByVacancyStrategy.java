package com.example.car_pooling.Service.Strategy;

import com.example.car_pooling.DTO.TripFilteringRequestDto;
import com.example.car_pooling.Entities.Trip;
import com.example.car_pooling.Manager.TripManager;
import com.example.car_pooling.Service.Strategy.StrategyInterfaces.RoutingStrategy;
import com.example.car_pooling.Service.Strategy.StrategyInterfaces.TripFilteringStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TripFilteringByVacancyStrategy implements TripFilteringStrategy {

    @Autowired
    private TripManager tripManager;

    @Override
    public List<Trip> filterTrips(TripFilteringRequestDto tripFilterRequest) {
        RoutingStrategy routingStrategy = new MutiRouteStrategy();
        List<List<Trip>> trips = routingStrategy.getRoute(
                tripFilterRequest.getOriginStateCode(),
                tripFilterRequest.getDestinationStateCode(),
                tripFilterRequest.getSeatsAvailable());
        if (trips.isEmpty()) {
            return null;
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
