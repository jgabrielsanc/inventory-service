package com.jsanchez.inventory.infraestructure.adapter.out.event;

import com.jsanchez.inventory.application.port.out.EventPublisherPort;
import com.jsanchez.inventory.domain.event.ProductCreatedEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SpringEventPublisherAdapter implements EventPublisherPort {

    private final ApplicationEventPublisher publisher;

    @Override
    public void publish(ProductCreatedEvent event) {
        publisher.publishEvent(event);
    }
}
