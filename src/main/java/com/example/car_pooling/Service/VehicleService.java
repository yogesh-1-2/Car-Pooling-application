package com.example.car_pooling.Service;

import com.example.car_pooling.Entities.Enums.AssetType;
import com.example.car_pooling.Entities.UserResources;
import com.example.car_pooling.Entities.Vehicle;
import com.example.car_pooling.Manager.UserResourceManager;
import com.example.car_pooling.Manager.VehicleManager;
import com.example.car_pooling.Validations.VehicleValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    @Autowired
    private VehicleManager vehicleManager;

    @Autowired
    private UserResourceManager userResourceManager;

    @Autowired
    private VehicleValidation vehicleValidation;

    public void registerVehicle(Vehicle vehicle, Integer userId) {
        vehicleValidation.validateUserVehicle(vehicle, userId);
        UserResources userResource = UserResources.builder().userId(userId).
                assetId(vehicle.getVehicleNumber()).assetType(AssetType.VEHICLE).build();
        userResourceManager.addUserResource(userResource);
        vehicleManager.registerVehicle(vehicle);
    }

    public List<Vehicle> getAllVehiclesByUserId(Integer userId) {
        return vehicleManager.getAllVehicles(userId);
    }
}
