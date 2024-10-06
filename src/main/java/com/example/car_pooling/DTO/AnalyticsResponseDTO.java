package com.example.car_pooling.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AnalyticsResponseDTO {
    private int userId;
    private String name;
    private int rideOffered;
    private int rideTaken;
}
