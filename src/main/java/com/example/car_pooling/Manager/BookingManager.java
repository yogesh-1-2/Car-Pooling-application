package com.example.car_pooling.Manager;

import com.example.car_pooling.Entities.Booking;
import com.example.car_pooling.Entities.Trip;
import com.example.car_pooling.Repository.BookingRepository;
import com.example.car_pooling.Repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookingManager {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    TripRepository tripRepository;

    public void bookTrip(Booking booking) {
        List<Trip> trips = tripRepository.getTripsByIds(booking.getTripIds());
        trips.forEach( trip -> {
                    trip.setAvailableSeats(trip.getAvailableSeats() - booking.getSeatsBooked());
                    tripRepository.upsertTrip(trip);
                });
        bookingRepository.addBooking(booking);
    }

    public Booking getBookingById(Integer bookingId) {
        return bookingRepository.getBookingById(bookingId);
    }
}
