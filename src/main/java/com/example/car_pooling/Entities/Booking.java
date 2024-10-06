package com.example.car_pooling.Entities;

import lombok.Data;

import java.util.List;

@Data
public class Booking {
    private Integer       bookingId;
    private List<Integer> tripIds;
    private Integer       userId;
    private Integer       seatsBooked;
}
