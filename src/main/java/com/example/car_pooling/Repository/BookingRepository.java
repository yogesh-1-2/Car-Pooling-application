package com.example.car_pooling.Repository;

import com.example.car_pooling.Entities.Booking;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class BookingRepository {

    private final List<Booking> bookings = new ArrayList<>();

    // Add a booking
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    // Get a booking by booking ID
    public Booking getBookingById(Integer bookingId) {
        return bookings.stream()
                .filter(booking -> booking.getBookingId().equals(bookingId))
                .findFirst()
                .orElse(null);
    }

    // Find bookings by user ID
    public List<Booking> getBookingsByUserId(Integer userId) {
        return bookings.stream()
                .filter(booking -> booking.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

}

