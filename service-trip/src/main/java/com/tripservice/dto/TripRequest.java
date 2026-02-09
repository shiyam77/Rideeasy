package com.tripservice.dto;

import lombok.Data;

@Data
public class TripRequest {
    private String passengerName;
    private String pickupLocation;
    private String destination;
}