package com.example.car_pooling.Util;

import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class Constants {
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String PHONE_NUMBER = "phone number";
    public static final String VEHICLE_NUMBER = "vehicle number";
    public static final String TYRES = "tyres";
    public static final String SEATS = "seats";
    public static final String BRAND = "brand";
    public static final String MODEL = "model";
    public static final String OWNER_ID = "owner id";
    public static final String VEHICLE_ID = "vehicle id";
    public static final String ADDRESS = "address";
    public static final String ORIGIN = "origin";
    public static final String DESTINATION = "destination";
    public static final String SEATS_AVAILABLE = "seats available";

    // Map to store state codes with state names in lowercase
    public static final Map<String, Integer> stateCodeMap = new HashMap<>();

    static {
        stateCodeMap.put("andhra pradesh", 28);
        stateCodeMap.put("arunachal pradesh", 12);
        stateCodeMap.put("assam", 18);
        stateCodeMap.put("bihar", 10);
        stateCodeMap.put("chhattisgarh", 22);
        stateCodeMap.put("goa", 30);
        stateCodeMap.put("gujarat", 24);
        stateCodeMap.put("haryana", 6);
        stateCodeMap.put("himachal pradesh", 2);
        stateCodeMap.put("jharkhand", 20);
        stateCodeMap.put("karnataka", 29);
        stateCodeMap.put("kerala", 32);
        stateCodeMap.put("madhya pradesh", 23);
        stateCodeMap.put("maharashtra", 27);
        stateCodeMap.put("manipur", 14);
        stateCodeMap.put("meghalaya", 17);
        stateCodeMap.put("mizoram", 15);
        stateCodeMap.put("nagaland", 13);
        stateCodeMap.put("odisha", 21);
        stateCodeMap.put("punjab", 3);
        stateCodeMap.put("rajasthan", 8);
        stateCodeMap.put("sikkim", 11);
        stateCodeMap.put("tamil nadu", 33);
        stateCodeMap.put("telangana", 36);
        stateCodeMap.put("tripura", 16);
        stateCodeMap.put("uttar pradesh", 9);
        stateCodeMap.put("uttarakhand", 5);
        stateCodeMap.put("west bengal", 19);
    }
}
