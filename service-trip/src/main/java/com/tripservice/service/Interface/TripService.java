package com.tripservice.service;

import com.tripservice.dto.TripRequest;
import com.tripservice.entity.Trip;

public interface TripService {
    Trip createTrip(TripRequest request);
    Trip updateTripStatus(String tripId, String status);
}