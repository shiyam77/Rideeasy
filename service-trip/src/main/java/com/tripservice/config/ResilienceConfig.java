package com.tripservice.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.core.registry.EntryAddedEvent;
import io.github.resilience4j.core.registry.RegistryEventConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResilienceConfig {

    @Bean
    public RegistryEventConsumer<CircuitBreaker> myRegistryEventConsumer() {
        return new RegistryEventConsumer<>() {
            @Override
            public void onEntryAddedEvent(EntryAddedEvent<CircuitBreaker> entryAddedEvent) {
                // This triggers every time a new Circuit Breaker is created
                entryAddedEvent.getAddedEntry().getEventPublisher()
                        .onStateTransition(event -> {
                            System.out.println("--- CIRCUIT BREAKER STATUS CHANGE ---");
                            System.out.println("Circuit Breaker: " + event.getCircuitBreakerName());
                            System.out.println("Transition: " + event.getStateTransition());
                        });
            }

            @Override
            public void onEntryRemovedEvent(EntryAddedEvent<CircuitBreaker> entryRemoveEvent) {
                // Optional: handle removal
            }

            @Override
            public void onEntryReplacedEvent(EntryAddedEvent<CircuitBreaker> entryReplacedEvent) {
                // Optional: handle replacement
            }
        };
    }
}