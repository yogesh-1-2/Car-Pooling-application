package com.example.car_pooling.Repository;

import com.example.car_pooling.Entities.Trip;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TripRepository {
    private List<Trip> trips;
    private Integer count;

    public TripRepository( ) {
        trips = new ArrayList<>();
        count = 1;
    }

    public void upsertTrip(Trip trip) {
        boolean found = false;
        for (int i = 0; i < trips.size(); i++) {
            if (trips.get(i).getId().equals(trip.getId())) {
                trips.set(i, trip);
                found = true;
                break;
            }
        }

        if (!found) {
            trip.setId(count++);
            trips.add(trip);
        }
    }

    public List<Trip> getTripsByOwner(Integer ownerId) {
        List<Trip> ownerTrips = new ArrayList<>();
        trips.forEach(trip -> {
            if (Objects.equals(trip.getOwnerId(), ownerId)) {
                ownerTrips.add(trip);
            }
        });
        return ownerTrips;
    }

    public Trip getTripById(int id){
        for(Trip trip: trips){
            if(trip.getId() == id){
                return trip;
            }
        }
        return null;
    }

    public List<Trip> getTripsByIds(List<Integer> tripsIds) {
        List<Trip> requiredTrips = new ArrayList<>();
        for(Trip trip: trips){
            if(tripsIds.contains(trip.getId())){
                requiredTrips.add(trip);
            }
        }
        return requiredTrips;
    }

    public List<Trip> getAllTripByUserId(int userId) {
        List<Trip> userTrips = new ArrayList<>();
        for(Trip trip: trips){
            if(trip.getOwnerId() == userId){
                userTrips.add(trip);
            }
        }
        return userTrips;
    }

    public List<Trip> getTripsByOriginDest(Integer originState, Integer destinationState) {
        List<Trip> requiredTrips = new ArrayList<>();
        for(Trip trip: trips){
            if (trip.getOrigin().getStateCode().equals(originState) &&
                trip.getDestination().getStateCode().equals(destinationState)
            ) {
                requiredTrips.add(trip);
            }
        }
        return requiredTrips;
    }
}
