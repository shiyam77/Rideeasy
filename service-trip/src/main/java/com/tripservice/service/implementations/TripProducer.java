package com.tripservice.service.implementation;

import com.tripservice.entity.Trip;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@service
public class TripProducer {

    private  static  final  String Topic = "trip_events";
    private final kafkaTemplate <String ,trip> kafkaTemplate;

    public TripProducer(KafkaTemplate<String, Trip> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Trip trip) {
        // This sends the trip data to the "trip_events" topic
        this.kafkaTemplate.send(TOPIC, trip);
        System.out.println("Published event to Kafka: " + trip.getId());
    }
}