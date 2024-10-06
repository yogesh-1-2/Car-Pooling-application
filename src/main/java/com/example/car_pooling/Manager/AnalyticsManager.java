package com.example.car_pooling.Manager;

import com.example.car_pooling.DTO.AnalyticsResponseDTO;
import com.example.car_pooling.Entities.Booking;
import com.example.car_pooling.Entities.Trip;
import com.example.car_pooling.Entities.User;
import com.example.car_pooling.Repository.BookingRepository;
import com.example.car_pooling.Repository.TripRepository;
import com.example.car_pooling.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AnalyticsManager {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TripRepository tripRepository;

    public List<AnalyticsResponseDTO> getAnalytics() {
        List<User> users = userRepository.getUsers();
        List<Trip> trips = tripRepository.getAllTrips();
        List<Booking> bookings = bookingRepository.getAllBookings();

        Map<Integer, AnalyticsResponseDTO> analyticsMap = new HashMap<>();
        for (User user : users) {
            Integer userId = user.getId();
            AnalyticsResponseDTO userAnalytics = new AnalyticsResponseDTO();
            userAnalytics.setUserId(userId);
            userAnalytics.setName(user.getName());
            analyticsMap.put(userId, userAnalytics);
        }
        for (Trip trip : trips) {
            if (Objects.nonNull(trip.getEndTime())) {
                Integer ownerId = trip.getOwnerId();
                AnalyticsResponseDTO analyticsResponse = analyticsMap.get(ownerId);
                analyticsResponse.setRideOffered(analyticsResponse.getRideOffered() + 1);
                analyticsMap.put(ownerId, analyticsResponse);
            }
        }

        for (Booking booking : bookings) {
            Integer passengerId = booking.getUserId();
            AnalyticsResponseDTO analyticsResponse = analyticsMap.get(passengerId);
            analyticsResponse.setRideTaken(analyticsResponse.getRideTaken() + 1);
            analyticsMap.put(passengerId, analyticsResponse);
        }

        return new ArrayList<>(analyticsMap.values());
    }
}
