package com.jsanchez.inventory.infraestructure.adapter.in.rest.mapper;

import com.jsanchez.inventory.domain.model.Product;
import com.jsanchez.inventory.infraestructure.adapter.in.rest.dto.CreateProductRequest;
import com.jsanchez.inventory.infraestructure.adapter.in.rest.dto.ProductResponse;

public class ProductRestMapper {

    public static Product toDomain(CreateProductRequest request) {
        return Product
                .builder()
                .name(request.name())
                .quantity(request.quantity())
                .build();
    }

    public static ProductResponse toResponse(Product product) {
        return new ProductResponse(product.getId().toString(), product.getName(), product.getQuantity());
    }
}
