package com.example.car_pooling.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Attributes {
    private String      model;
    private String      color;
    private String      brand;
    private Integer     expiry;
}
