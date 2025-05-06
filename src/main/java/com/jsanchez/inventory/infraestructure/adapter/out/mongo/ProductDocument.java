package com.jsanchez.inventory.infraestructure.adapter.out.mongo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Document("products")
public class ProductDocument {

    @Id
    private UUID id;
    private String name;
    private int quantity;
}
