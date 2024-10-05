package com.example.car_pooling.Validations;

import ch.qos.logback.core.util.StringUtil;
import com.example.car_pooling.Entities.Enums.AssetType;
import com.example.car_pooling.Entities.UserResources;
import com.example.car_pooling.Entities.Vehicle;
import com.example.car_pooling.Entities.VehicleAttributes;
import com.example.car_pooling.Exceptions.VehicleExceptions;
import com.example.car_pooling.Manager.UserResourceManager;
import com.example.car_pooling.Repository.UserResourceRepository;
import com.example.car_pooling.Util.Constants;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class VehicleValidation {

    @Autowired
    private UserResourceManager userResourceManager;

    public void validateVehicle(Vehicle vehicle) {
        if (Objects.isNull(vehicle)) {
            throw new VehicleExceptions.VehicleNotFoundException();
        }
        validateVehicleAttribute(vehicle.getAttributes());
        if (StringUtil.isNullOrEmpty(vehicle.getVehicleNumber())) {
            throw new VehicleExceptions.VehicleFieldNotFoundException(Constants.VEHICLE_NUMBER);
        }
    }

    public void validateVehicleAttribute(VehicleAttributes attributes) {
        if (Objects.isNull(attributes)) {
            throw new VehicleExceptions.VehicleAttributesNotFoundException();
        }

        if (attributes.getTyres() <= 0) {
            throw new VehicleExceptions.VehicleFieldNotFoundException(Constants.TYRES);
        }

        if (attributes.getSeats() <= 0) {
            throw new VehicleExceptions.VehicleFieldNotFoundException(Constants.SEATS);
        }

        if (StringUtil.isNullOrEmpty(attributes.getModel())) {
            throw new VehicleExceptions.VehicleFieldNotFoundException(Constants.MODEL);
        }
    }

    public void validateUserVehicle(Vehicle vehicle, Integer userId) {
        Integer vehicleOwnerId = userResourceManager.getUserIdByAssetId(vehicle.getVehicleNumber());
        if (Objects.nonNull(vehicleOwnerId) && !vehicleOwnerId.equals(userId)) {
            throw new VehicleExceptions.VehicleAlreadyOwnedException();
        }
    }
}
