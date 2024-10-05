package com.example.car_pooling.Controller;

import com.example.car_pooling.Entities.UserResources;
import com.example.car_pooling.Entities.Vehicle;
import com.example.car_pooling.Service.VehicleService;
import com.example.car_pooling.Validations.VehicleValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleValidation vehicleValidation;

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> registerVehicle(@RequestBody Vehicle vehicle, @RequestParam Integer userId) {
        vehicleValidation.validateVehicle(vehicle);
        vehicleService.registerVehicle(vehicle, userId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<Vehicle>> getAllVehicles(@RequestParam Integer userId) {
        return new ResponseEntity<>(vehicleService.getAllVehiclesByUserId(userId), HttpStatus.OK);
    }

}
