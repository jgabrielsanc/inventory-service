package com.jsanchez.inventory.application.port.out;

import com.jsanchez.inventory.domain.model.Product;

public interface SaveProductPort {

    Product save(Product product);
}
