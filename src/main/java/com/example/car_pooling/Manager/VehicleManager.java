package com.example.car_pooling.Manager;

import com.example.car_pooling.Entities.Enums.AssetType;
import com.example.car_pooling.Entities.UserResources;
import com.example.car_pooling.Entities.Vehicle;
import com.example.car_pooling.Repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VehicleManager {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserResourceManager userResourceManager;

    public void registerVehicle(Vehicle vehicle) {
        vehicleRepository.addVehicle(vehicle);
    }

    public List<Vehicle> getAllVehicles(Integer userId) {
        List<UserResources> userResources = userResourceManager.getUserResourcesByUserIdAndAssetType(userId, AssetType.VEHICLE);
        List<String>vehicleIds = userResources.stream().map(UserResources::getAssetId).toList();
        return vehicleRepository.getVehicles(vehicleIds);
    }

    public Vehicle getVehicleByNumber(String vehicleNumber){
        return vehicleRepository.getVehicleByNumber(vehicleNumber);
    }
}
