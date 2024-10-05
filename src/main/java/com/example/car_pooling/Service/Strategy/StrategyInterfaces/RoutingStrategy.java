package com.example.car_pooling.Service.Strategy.StrategyInterfaces;

import com.example.car_pooling.Entities.Trip;

import java.util.List;

public interface RoutingStrategy {
    public List<List<Trip>> getRoute(Integer originStateCode, Integer destinationStateCode, Integer seatsAvailable);
}
