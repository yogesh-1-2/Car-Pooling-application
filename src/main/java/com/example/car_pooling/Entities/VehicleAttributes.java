package com.example.car_pooling.Entities;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@NoArgsConstructor
public class VehicleAttributes extends Attributes {
    private int seats;
    private int tyres;
    private Boolean is4x4;
}
