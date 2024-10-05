package com.example.car_pooling.Validations;

import com.example.car_pooling.Entities.Booking;
import com.example.car_pooling.Entities.Trip;
import com.example.car_pooling.Exceptions.BookingExceptions;
import com.example.car_pooling.Exceptions.TripExceptions;
import com.example.car_pooling.Manager.TripManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BookingValidation {

    @Autowired
    private TripManager tripManager;

    public void validateBooking(Booking booking) {
        if (Objects.isNull(booking)) {
            throw new BookingExceptions.BookingNotFoundException();
        }

        Trip trip = tripManager.getTripById(booking.getTripId());

        if (Objects.isNull(trip)) {
            throw new TripExceptions.TripNotFoundException();
        }

        if (trip.getOwnerId().equals(booking.getUserId())) {
            throw new BookingExceptions.InvalidBookingUserException();
        }

        if (trip.getAvailableSeats() < booking.getSeatsBooked()) {
            throw new BookingExceptions.SeatsNotAvailableException();
        }
    }
}
