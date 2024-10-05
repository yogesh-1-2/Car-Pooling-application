package com.example.car_pooling.Service.Strategy.StrategyInterfaces;

import com.example.car_pooling.DTO.TripFilteringRequestDto;
import com.example.car_pooling.Entities.Trip;

import java.util.List;

public interface TripFilteringStrategy {
    List<Trip> filterTrips(TripFilteringRequestDto tripFilterRequest);
}

