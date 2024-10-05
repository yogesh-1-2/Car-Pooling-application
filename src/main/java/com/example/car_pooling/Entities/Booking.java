package com.example.car_pooling.Entities;

import lombok.Data;

import java.util.List;

@Data
public class Booking {
    private Integer       bookingId;
    private Integer       tripId;
    private Integer       userId;
    private Integer       seatsBooked;
}
