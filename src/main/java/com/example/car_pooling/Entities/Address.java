package com.example.car_pooling.Entities;

import lombok.Data;

@Data
public class Address {
    private String city;
    private String state;
    private Integer stateCode;
    private String zipCode;
}
