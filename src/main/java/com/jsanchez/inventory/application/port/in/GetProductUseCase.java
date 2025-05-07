package com.jsanchez.inventory.application.port.in;

import com.jsanchez.inventory.domain.model.Product;

import java.util.UUID;

public interface GetProductUseCase {
    Product getProduct(UUID id);
}
