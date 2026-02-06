package service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @CircuitBreaker(name = "kafkaService", fallbackMethod = "kafkaFallback")
    public void sendNotification(String message) {
        kafkaTemplate.send("notification-topic", message);
    }

    // This runs if Kafka is unreachable
    public void kafkaFallback(String message, Throwable t) {
        System.err.println("Kafka is down! Logging message locally: " + message);
        // Better yet: Save to a database table called 'failed_notifications' for later retry
    }
}