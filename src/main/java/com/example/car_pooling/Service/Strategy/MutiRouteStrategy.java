package com.example.car_pooling.Service.Strategy;

import com.example.car_pooling.Entities.Trip;
import com.example.car_pooling.Manager.TripManager;
import com.example.car_pooling.Service.Strategy.StrategyInterfaces.RoutingStrategy;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class MutiRouteStrategy implements RoutingStrategy {

    @AllArgsConstructor
    private static class StateNode {
        public Integer stateCode;
        public List<Trip> trips;
    }

    @Autowired
    TripManager tripManager;

    @Override
    public List<List<Trip>> getRoute(Integer originStateCode, Integer destinationStateCode, Integer seatsAvailable) {
        List<Trip> trips = tripManager.getActiveTripsByOrignDest(originStateCode, destinationStateCode);
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

        // BFS to explore all possible routes
        while (!queue.isEmpty()) {
            StateNode current = queue.poll();
            Integer currentState = current.stateCode;
            List<Trip> currentRoute = current.trips;

            // If the destination is reached, add the route to the list of valid routes
            if (currentState.equals(destinationStateCode)) {
                allRoutes.add(currentRoute);
                continue;
            }

            // Explore all trips from the current state
            if (tripMap.containsKey(currentState)) {
                for (Trip nextTrip : tripMap.get(currentState)) {
                    // Create a new route by adding the next trip to the current route
                    List<Trip> newRoute = new ArrayList<>(currentRoute);
                    newRoute.add(nextTrip);
                    queue.add(new StateNode(nextTrip.getDestination().getStateCode(), newRoute));
                }
            }
        }

        // Return the first valid route found, or null if no route exists
        return allRoutes.isEmpty() ? null : allRoutes;
    }
}
