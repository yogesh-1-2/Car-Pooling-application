package com.example.car_pooling.Manager;

import com.example.car_pooling.Entities.Enums.AssetType;
import com.example.car_pooling.Entities.User;
import com.example.car_pooling.Entities.UserResources;
import com.example.car_pooling.Repository.UserResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class UserResourceManager {

    @Autowired
    private UserResourceRepository userResourcesRepository;

    public void addUserResource(UserResources userResources) {
        userResourcesRepository.addResource(userResources);
    }

    public List<UserResources> getUserResourcesByUserIdAndAssetType(Integer userId, AssetType assetType) {
        return userResourcesRepository.getResourcesByUserIdAndType(userId, assetType);
    }

    public Integer getUserIdByAssetId(String assetId) {
         UserResources userResources = userResourcesRepository.getUserResourceByAssetId(assetId);
         if (Objects.isNull(userResources)) {
             return null;
         }
         return userResources.getUserId();
    }

}
