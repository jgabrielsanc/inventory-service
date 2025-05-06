package com.jsanchez.inventory.application.port.in;

import com.jsanchez.inventory.domain.model.Product;

public interface CreateProductUseCase {
    Product createProduct(String name, int quantity);
}
