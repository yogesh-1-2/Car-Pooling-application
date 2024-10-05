package com.example.car_pooling.Controller;

import com.example.car_pooling.Service.BookingService;
import com.example.car_pooling.Validations.TripValidation;
import com.example.car_pooling.Validations.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trip/bookings")
public class BookingController {

    @Autowired
    UserValidation userValidation;

    @Autowired
    TripValidation tripValidation;

    @Autowired
    BookingService bookingService;

    @PostMapping("/book/{tripId}")
    public ResponseEntity<HttpStatus> bookTrip(@PathVariable Integer tripId, @RequestParam Integer userId, @RequestParam Integer seatsBooked) {
        userValidation.validateUser(userId);
        tripValidation.validateTrip(tripId);
        bookingService.bookTrip(tripId, userId, seatsBooked);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
