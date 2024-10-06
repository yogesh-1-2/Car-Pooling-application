package com.example.car_pooling.Service.Strategy;

import com.example.car_pooling.Entities.Trip;
import com.example.car_pooling.Manager.TripManager;
import com.example.car_pooling.Service.Strategy.StrategyInterfaces.RoutingStrategy;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MutiRouteStrategy implements RoutingStrategy {

    @AllArgsConstructor
    private class StateNode {
        public Integer stateCode;
        public List<Trip> trips;
    }

    @Autowired
    private TripManager tripManager;

    @Override
    public List<List<Trip>> getRoute(Integer originStateCode, Integer destinationStateCode, Integer seatsAvailable) {
        List<Trip> trips = tripManager.getAllActiveTrips();
        trips = trips.stream()
                .filter(trip -> trip.getAvailableSeats() >= seatsAvailable)
                .toList();

        // Map to store available trips from each state
        Map<Integer, List<Trip>> tripMap = new HashMap<>();
        for (Trip trip : trips) {
            tripMap.putIfAbsent(trip.getOrigin().getStateCode(), new ArrayList<>());
            tripMap.get(trip.getOrigin().getStateCode()).add(trip);
        }

        Queue<StateNode> queue = new LinkedList<>();
        List<List<Trip>> allRoutes = new ArrayList<>();

        if (tripMap.containsKey(originStateCode)) {
            for (Trip trip : tripMap.get(originStateCode)) {
                queue.add(new StateNode(trip.getDestination().getStateCode(), new ArrayList<>(List.of(trip))));
            }
        }

        Set<Integer> visited = new HashSet<>();  // Set to keep track of visited states

        while (!queue.isEmpty()) {
            StateNode current = queue.poll();
            Integer currentState = current.stateCode;
            List<Trip> currentRoute = current.trips;

            if (currentState.equals(destinationStateCode)) {
                allRoutes.add(currentRoute);
                continue;
            }

            if (visited.contains(currentState)) {
                continue;
            }

            visited.add(currentState);

            if (tripMap.containsKey(currentState)) {
                for (Trip nextTrip : tripMap.get(currentState)) {
                    List<Trip> newRoute = new ArrayList<>(currentRoute);
                    newRoute.add(nextTrip);

                    if (!visited.contains(nextTrip.getDestination().getStateCode())) {
                        queue.add(new StateNode(nextTrip.getDestination().getStateCode(), newRoute));
                    }
                }
            }
        }

        return allRoutes.isEmpty() ? null : allRoutes;
    }
}
