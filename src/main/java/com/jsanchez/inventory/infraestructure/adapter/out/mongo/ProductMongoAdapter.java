package com.jsanchez.inventory.infraestructure.adapter.out.mongo;

import com.jsanchez.inventory.application.port.out.LoadProductPort;
import com.jsanchez.inventory.application.port.out.SaveProductPort;
import com.jsanchez.inventory.domain.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class ProductMongoAdapter implements SaveProductPort, LoadProductPort {

    private final SpringDataProductRepository repository;


    @Override
    public void save(Product product) {
        ProductDocument doc = new ProductDocument();
        doc.setId(product.getId());
        doc.setName(product.getName());
        doc.setQuantity(product.getQuantity());
        repository.save(doc);
    }

    @Override
    public Optional<Product> loadById(UUID id) {
        return repository.findById(id)
                .map(doc ->
                        new Product(doc.getId(), doc.getName(), doc.getQuantity())
                );
    }
}
