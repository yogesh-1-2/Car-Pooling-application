package com.example.car_pooling.Controller;

import com.example.car_pooling.Service.BookingService;
import com.example.car_pooling.Validations.TripValidation;
import com.example.car_pooling.Validations.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trip/bookings")
public class BookingController {

    @Autowired
    UserValidation userValidation;

    @Autowired
    TripValidation tripValidation;

    @Autowired
    BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<HttpStatus> bookTrip(@RequestParam List<Integer> tripIds, @RequestParam Integer userId, @RequestParam Integer seatsBooked) {
        userValidation.validateUser(userId);
        tripValidation.validateTrip(tripIds);
        bookingService.bookTrip(userId, tripIds, seatsBooked);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
