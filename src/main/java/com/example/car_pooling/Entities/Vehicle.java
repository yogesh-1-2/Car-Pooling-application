package com.example.car_pooling.Entities;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Vehicle {
    private Integer id;
    private VehicleAttributes attributes;
    private String vehicleNumber;
}
