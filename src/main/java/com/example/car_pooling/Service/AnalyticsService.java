package com.example.car_pooling.Service;

import com.example.car_pooling.DTO.AnalyticsResponseDTO;
import com.example.car_pooling.Manager.AnalyticsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticsService {

    @Autowired
    private AnalyticsManager analyticsManager;

    public List<AnalyticsResponseDTO> getAnalytics() {
        return analyticsManager.getAnalytics();
    }
}
