package com.example.car_pooling.Entities;

import com.example.car_pooling.Entities.Enums.AssetType;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserResources {
    private Integer        userId;
    private String       assetId;
    private AssetType    assetType;
}
