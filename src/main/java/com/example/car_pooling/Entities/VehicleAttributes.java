package com.example.car_pooling.Entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@NoArgsConstructor
public class VehicleAttributes extends Attributes {
//    @Builder
//    public VehicleAttributes(String model, String color, String brand, Integer expiry, int seats, int tyres, boolean is4x4) {
//        super(model, color, brand, expiry);
//        this.seats = seats;
//        this.tyres = tyres;
//        this.is4x4 = is4x4;
//    }
    private int seats;
    private int tyres;
    private Boolean is4x4;
}
