package com.example.car_pooling.Repository;

import com.example.car_pooling.Entities.Booking;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.stream.Collectors;

@Component
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

    // Find bookings by trip ID
    public List<Booking> getBookingsByTripId(Integer tripId) {
        return bookings.stream()
                .filter(booking -> booking.getTripId().equals(tripId))
                .collect(Collectors.toList());
    }

    // Get all bookings
    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings);
    }
}

