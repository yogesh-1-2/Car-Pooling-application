package com.example.car_pooling.Controller;

import com.example.car_pooling.Entities.Enums.TripSelectionEnums;
import com.example.car_pooling.Entities.Trip;
import com.example.car_pooling.Service.TripService;
import com.example.car_pooling.Validations.TripValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/trip")
public class TripController {

    @Autowired
    private TripService tripService;

    @Autowired
    private TripValidation tripValidation;

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createTrip(@RequestBody Trip trip, @RequestParam Integer ownerId) {
        tripValidation.validateTrip(trip);
        tripService.createTrip(trip, ownerId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/endTrip/{tripId}")
    public ResponseEntity<HttpStatus> updateTrip(@PathVariable Integer tripId, @RequestParam Integer ownerId) {
        tripService.endTrip(tripId, ownerId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("")
    public ResponseEntity<List<Trip>> getAllTrips(@RequestParam Integer userId) {
        return new ResponseEntity<>(tripService.getAllTripByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Trip>> getTrip(
            @RequestParam Integer originState,
            @RequestParam Integer destinationState,
            @RequestParam TripSelectionEnums tripSelectionStrategy,
            @RequestParam Integer seatsRequired,
            @RequestParam(required = false) String vehicleType) {

        return new ResponseEntity<>(tripService.searchTrip(originState, destinationState, tripSelectionStrategy, vehicleType, seatsRequired), HttpStatus.OK);
    }
}
