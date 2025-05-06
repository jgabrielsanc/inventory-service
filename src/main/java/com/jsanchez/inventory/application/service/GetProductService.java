package com.jsanchez.inventory.application.service;

import com.jsanchez.inventory.application.port.in.GetProductUseCase;
import com.jsanchez.inventory.application.port.out.LoadProductPort;
import com.jsanchez.inventory.domain.model.Product;

import java.util.Optional;
import java.util.UUID;

public class GetProductService implements GetProductUseCase {

    private final LoadProductPort loadProductPort;

    public GetProductService(LoadProductPort loadProductPort) {
        this.loadProductPort = loadProductPort;
    }

    @Override
    public Optional<Product> getProductById(UUID id) {
        return loadProductPort.loadById(id);
    }
}
