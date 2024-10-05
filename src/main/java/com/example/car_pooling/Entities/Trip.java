package com.example.car_pooling.Entities;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Trip {
    private Integer id;
    private Address origin;
    private Address destination;
    private Integer availableSeats;
    private Integer ownerId;
    private String  vehicleNumber;
    private Long    endTime;
}
