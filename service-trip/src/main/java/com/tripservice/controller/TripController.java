package com.tripservice.controller;

import com.tripservice.dto.TripRequest; // Using your DTO folder
import com.tripservice.entity.Trip;      // Using your Entity folder
import com.tripservice.service.TripService; // Using your Service folder
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/trips")
public class TripController {

    // Dependency Injection: Bringing in your Service Implementation
    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripDetails(
            @PathVariable("id") String tripId,
            @RequestParam(value = "driverName", required = false) String driver) {
        Trip trip = tripService.getTripById(tripId);
        return ResponseEntity.ok(trip);
    }

    @PostMapping("/book")
    public Trip createTrip(@RequestBody TripRequest request) {
        // Now using your Service Implementation to handle the logic
        return tripService.createTrip(request);
    }
}