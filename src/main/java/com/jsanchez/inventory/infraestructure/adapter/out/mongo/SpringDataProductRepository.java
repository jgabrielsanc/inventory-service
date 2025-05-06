package com.jsanchez.inventory.infraestructure.adapter.out.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface SpringDataProductRepository extends MongoRepository<ProductDocument, UUID> {
}
