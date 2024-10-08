package com.example.car_pooling.Repository;

import com.example.car_pooling.Entities.Vehicle;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class VehicleRepository {
    private final List<Vehicle> vehicles;
    private       int           counter = 0;
    VehicleRepository() {
        vehicles = new ArrayList<>();
        counter = 1;
    }

    public void addVehicle(Vehicle vehicle){
        if (Objects.isNull(vehicle.getId())) {
            vehicle.setId(counter++);
            vehicles.add(vehicle);
        } else {
            for (int i = 0; i < vehicles.size(); i++) {
                if (vehicles.get(i).getId().equals(vehicle.getId())) {
                    vehicles.set(i, vehicle); // Update the element in the list
                }
            }
        }
    }

    public void removeVehicle(Vehicle vehicle){
        vehicles.remove(vehicle);
    }

    public List<Vehicle> getVehicles(){
        return vehicles;
    }

    public Vehicle getVehicleById(int id){
        for(Vehicle vehicle: vehicles){
            if(vehicle.getId() == id){
                return vehicle;
            }
        }
        return null;
    }

    public List<Vehicle> getVehicles(List<String>vehicleNos) {
        List<Vehicle> vehicleList = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicleNos.contains(vehicle.getVehicleNumber())) {
                vehicleList.add(vehicle);
            }
        }
        return vehicleList;
    }

    public Vehicle getVehicleByNumber(String vehicleNumber){
        for(Vehicle vehicle: vehicles){
            if(vehicle.getVehicleNumber().equals(vehicleNumber)){
                return vehicle;
            }
        }
        return null;
    }
}
