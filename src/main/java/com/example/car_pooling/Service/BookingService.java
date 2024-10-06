package com.example.car_pooling.Service;

import com.example.car_pooling.Entities.Booking;
import com.example.car_pooling.Manager.BookingManager;
import com.example.car_pooling.Repository.BookingRepository;
import com.example.car_pooling.Validations.BookingValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    BookingManager bookingManager;

    @Autowired
    BookingValidation bookingValidation;

    public void bookTrip(Integer userId, List<Integer> tripIds, Integer seatsBooked) {
        Booking booking = new Booking();
        booking.setUserId(userId);
        booking.setTripIds(tripIds);
        booking.setSeatsBooked(seatsBooked);
        bookingValidation.validateBooking(booking);
        bookingManager.bookTrip(booking);
    }
}
