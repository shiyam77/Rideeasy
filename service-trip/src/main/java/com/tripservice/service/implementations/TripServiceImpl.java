package com.tripservice.service.implementation;

import com.tripservice.service.TripService;
import com.tripservice.dto.TripRequest;
import com.tripservice.entity.Trip;
import com.tripservice.repository.TripRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class TripServiceImpl implements TripService {

    private final TripRepository repository;

    public TripServiceImpl(TripRepository repository) {
        this.repository = repository;
    }

    @Override
    @CircuitBreaker(name = "identityService", fallbackMethod = "identityFallback")
    public String checkUserStatus(String userId) {
        // This makes the network call to the Identity Service
        return identityClient.getStatus(userId);
    }

    // The fallback logic stays here too
    public String identityFallback(String userId, Throwable t) {
        return "Identity Service is currently unavailable. Proceeding with safety defaults.";
    }

    @Override
    public Trip createTrip(TripRequest request) {
        Trip trip = new Trip();
        trip.setId(UUID.randomUUID().toString());
        trip.setPassengerName(request.getPassengerName());
        // Logic for saving and returning...
        tripProducer.sendMessage(trip);


        return trip;


    }

    @Override
    public Trip updateTripStatus(String tripId, String status) {
        // Logic to change the state
        return null;
    }

    @Override
    @Cacheable(value = "trips", key = "#tripId")
    public Trip getTripById(String tripId) {
        return repository.findById(tripId)
                .orElseThrow(() -> new TripNotFoundException("Trip with ID " + tripId + " was not found"));
    }

    @CacheEvict(value = "trips", key = "#tripId")
    public void deleteTrip(UUID tripId) {
        repository.deleteById(tripId);
    }
}