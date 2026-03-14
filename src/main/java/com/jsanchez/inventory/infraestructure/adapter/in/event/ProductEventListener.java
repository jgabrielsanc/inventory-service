package com.jsanchez.inventory.infraestructure.adapter.in.event;

import com.jsanchez.inventory.domain.event.ProductCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductEventListener {

    @Async
    @EventListener
    public void onProductCreated(ProductCreatedEvent event) {
        // TODO add implementation to kafka
        log.info("Product created (async): {} - {}", event.productId(), event.name());
    }
}
