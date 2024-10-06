package com.example.car_pooling.Controller;

import com.example.car_pooling.DTO.AnalyticsResponseDTO;
import com.example.car_pooling.Service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/analytics")
    public ResponseEntity<List<AnalyticsResponseDTO>> getAnalytics() {
        return new ResponseEntity<>(analyticsService.getAnalytics(), HttpStatus.OK);
    }
}
