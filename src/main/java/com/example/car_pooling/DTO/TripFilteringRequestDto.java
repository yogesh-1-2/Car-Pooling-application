package com.example.car_pooling.DTO;

import com.example.car_pooling.Entities.Enums.TripSelectionEnums;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TripFilteringRequestDto {
    private Integer originStateCode;
    private Integer destinationStateCode;
    private TripSelectionEnums tripSelectionStrategy;
    private String vehicleType;
    private Integer seatsAvailable;
}
