package com.tripservice.repository;

import com.tripservice.entity.Trip;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TripRepository extends JpaRepository<Trip, UUID> {
    private List<Trip> trips = new ArrayList<>();

    private final TripRepository repository;

    public TripServiceImpl(TripRepository repository) {
        this.repository = repository;
    }

    // 1. CREATE
    public Trip createTrip(TripRequest request) {
        Trip trip = new Trip();
        // ... set fields ...
        return repository.save(trip); // Built-in method
    }

    // 2. READ (Single)
    public Trip getTrip(UUID id) {
        return repository.findById(id).orElseThrow(); // Built-in method
    }

    // 3. READ (All)
    public List<Trip> getAllTrips() {
        return repository.findAll(); // Built-in method
    }

    // 4. UPDATE
    public Trip updateTrip(UUID id, TripRequest updates) {
        Trip existing = repository.findById(id).orElseThrow();
        existing.setDestination(updates.getDestination());
        return repository.save(existing); // .save() works for updates too!
    }

    // 5. DELETE
    public void deleteTrip(UUID id) {
        repository.deleteById(id); // Built-in method
    }
    List<Trip> findByPassengerName(String name);

    // Spring automatically generates: SELECT * FROM trips WHERE status = ?
    List<Trip> findByStatus(TripStatus status);

}