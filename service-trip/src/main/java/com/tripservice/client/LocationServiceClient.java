package com.tripservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "service-location") // This matches the spring.application.name of the location service
public interface LocationServiceClient {

    @GetMapping("/api/v1/location/coords")
    String getCoordinates(@RequestParam("address") String address);

    @GetMapping("/api/v1/geo/distance") // The exact endpoint inside the OTHER service
    double calculateDistance(@RequestParam String start, @RequestParam String end);
}