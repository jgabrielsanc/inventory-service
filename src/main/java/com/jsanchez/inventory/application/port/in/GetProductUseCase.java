package com.jsanchez.inventory.application.port.in;

import com.jsanchez.inventory.domain.model.Product;

import java.util.Optional;
import java.util.UUID;

public interface GetProductUseCase {
    Optional<Product> getProductById(UUID id);
}
