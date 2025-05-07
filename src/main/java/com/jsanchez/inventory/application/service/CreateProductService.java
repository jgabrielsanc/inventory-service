package com.jsanchez.inventory.application.service;

import com.jsanchez.inventory.application.port.in.CreateProductUseCase;
import com.jsanchez.inventory.application.port.out.EventPublisherPort;
import com.jsanchez.inventory.application.port.out.SaveProductPort;
import com.jsanchez.inventory.domain.event.ProductCreatedEvent;
import com.jsanchez.inventory.domain.model.Product;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class CreateProductService implements CreateProductUseCase {

    private final SaveProductPort saveProductPort;
    private final EventPublisherPort eventPublisherPort;

    public CreateProductService(SaveProductPort saveProductPort, EventPublisherPort eventPublisherPort) {
        this.saveProductPort = saveProductPort;
        this.eventPublisherPort = eventPublisherPort;
    }

    @Override
    public Product createProduct(Product product) {
        product.setId(UUID.randomUUID());
        saveProductPort.save(product);
        eventPublisherPort.publish(new ProductCreatedEvent(product.getId(), product.getName()));
        return product;

    }
}
