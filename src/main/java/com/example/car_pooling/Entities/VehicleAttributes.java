package com.example.car_pooling.Entities;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class VehicleAttributes extends Attributes {
    private int seats;
    private int tyres;
    private Boolean is4x4;
}
