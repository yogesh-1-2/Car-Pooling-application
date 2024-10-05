package com.example.car_pooling.Repository;

import com.example.car_pooling.Entities.Enums.AssetType;
import com.example.car_pooling.Entities.UserResources;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UserResourceRepository {
    private final List<UserResources> resources;

    UserResourceRepository(){
        resources = new ArrayList<>();
    }

    public void addResource(UserResources resource){
        resources.add(resource);
    }

    public void removeResource(UserResources resource){
        resources.remove(resource);
    }

    public List<UserResources> getResources(){
        return resources;
    }

    public List<UserResources> getResourcesByUserIdAndType(Integer userId, AssetType assetType) {
        List<UserResources> userResources = new ArrayList<>();
        resources.forEach(resource -> {
                    if (resource.getUserId().equals(userId) && resource.getAssetType().equals(assetType)){
                        userResources.add(resource);
                    }
                }
        );
        return userResources;
    }

    public UserResources getUserResourceByAssetId(String assetId) {
        for (UserResources resource : resources) {
            if (resource.getAssetId().equals(assetId)) {
                return resource;
            }
        }
        return null;
    }
}
