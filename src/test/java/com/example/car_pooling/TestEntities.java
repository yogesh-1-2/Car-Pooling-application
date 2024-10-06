package com.example.car_pooling;

import com.example.car_pooling.Entities.*;
import org.w3c.dom.Attr;

public class TestEntities {
    public static Trip getTrip1() {
        return Trip.builder()
                .availableSeats(3)
                .id(1)
                .vehicleNumber("af237")
                .origin(new Address("", "", 8, ""))
                .destination(new Address("", "", 22, ""))
                .build();
    }

    public static Vehicle getVehicle1() {
        VehicleAttributes attributes = VehicleAttributes.builder()
                .brand("")
                .color("")
                .model("Hero")
                .seats(4)
                .is4x4(true)
                .build();
        return Vehicle.builder()
                .id(1)
                .attributes(attributes)
                .vehicleNumber("af237")
                .build();
    }

    public static Trip getTrip2() {
        return Trip.builder()
                .availableSeats(3)
                .id(2)
                .vehicleNumber("af2334")
                .origin(new Address("", "", 26, ""))
                .destination(new Address("", "", 6, ""))
                .build();
    }

    public static Vehicle getVehicle2() {
        VehicleAttributes attributes = VehicleAttributes.builder()
                .brand("")
                .color("")
                .model("TVS")
                .seats(4)
                .is4x4(true)
                .build();
        return Vehicle.builder()
                .id(1)
                .attributes(attributes)
                .vehicleNumber("af2334")
                .build();
    }

    public static Trip getTrip3() {
        return Trip.builder()
                .availableSeats(3)
                .id(3)
                .vehicleNumber("af23374")
                .origin(new Address("", "", 22, ""))
                .destination(new Address("", "", 26, ""))
                .build();
    }

    public static Trip getTrip4() {
        return Trip.builder()
                .availableSeats(6)
                .id(4)
                .vehicleNumber("af8974")
                .origin(new Address("", "", 8, ""))
                .destination(new Address("", "", 22, ""))
                .build();
    }

    public static Vehicle getVehicle3() {
        VehicleAttributes attributes = VehicleAttributes.builder()
                .brand("")
                .color("")
                .model("Hero")
                .seats(4)
                .is4x4(true)
                .build();
        return Vehicle.builder()
                .id(1)
                .attributes(attributes)
                .vehicleNumber("af23374")
                .build();
    }

    public static Vehicle getVehicle4() {
        VehicleAttributes attributes = VehicleAttributes.builder()
                .brand("")
                .color("")
                .model("Hero")
                .seats(4)
                .is4x4(true)
                .build();
        return Vehicle.builder()
                .id(4)
                .attributes(attributes)
                .vehicleNumber("af8974")
                .build();
    }
}