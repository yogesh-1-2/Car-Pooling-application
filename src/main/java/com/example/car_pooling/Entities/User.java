package com.example.car_pooling.Entities;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {
    private Integer id;
    private String name;
    private String emailId;
    private String password;
    private String phoneNumber;
}
