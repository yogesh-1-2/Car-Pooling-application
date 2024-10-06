package com.example.car_pooling.Service.Strategy;

import com.example.car_pooling.DTO.TripFilteringRequestDto;
import com.example.car_pooling.Entities.Trip;
import com.example.car_pooling.Entities.Vehicle;
import com.example.car_pooling.Manager.TripManager;
import com.example.car_pooling.Manager.VehicleManager;
import com.example.car_pooling.Service.Strategy.StrategyInterfaces.RoutingStrategy;
import com.example.car_pooling.Service.Strategy.StrategyInterfaces.TripFilteringStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TripFilteringByVehicleStrategy implements TripFilteringStrategy {

    @Autowired
    private VehicleManager vehicleManager;

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
        for (List<Trip> tripRoute : trips) {
            boolean allVehiclesMatch = true;
            for (Trip trip : tripRoute) {
                Vehicle vehicle = vehicleManager.getVehicleByNumber(trip.getVehicleNumber());
                if (Boolean.FALSE.equals(vehicle.getAttributes().getModel().equalsIgnoreCase(tripFilterRequest.getVehicleType()))) {
                    allVehiclesMatch = false;
                    break;
                }
            }
            if (Boolean.TRUE.equals(allVehiclesMatch)) {
                return tripRoute;
            }
        }

        return Collections.emptyList();
    }
}
