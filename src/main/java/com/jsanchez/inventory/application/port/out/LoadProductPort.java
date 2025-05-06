package com.jsanchez.inventory.application.port.out;

import com.jsanchez.inventory.domain.model.Product;

import java.util.Optional;
import java.util.UUID;

public interface LoadProductPort {

    Optional<Product> loadById(UUID id);
}
