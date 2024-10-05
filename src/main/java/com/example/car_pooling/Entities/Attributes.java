package com.example.car_pooling.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attributes {
    private String      model;
    private String      color;
    private String      brand;
    private Integer     expiry;
}
