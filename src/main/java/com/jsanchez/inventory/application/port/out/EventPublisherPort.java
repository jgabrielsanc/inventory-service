package com.jsanchez.inventory.application.port.out;

import com.jsanchez.inventory.domain.event.ProductCreatedEvent;

public interface EventPublisherPort {
    void publish(ProductCreatedEvent event);
}
