package com.example.car_pooling.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {
    private String city;
    private String state;
    private Integer stateCode;
    private String zipCode;
}
