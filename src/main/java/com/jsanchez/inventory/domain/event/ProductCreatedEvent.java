package com.jsanchez.inventory.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ProductCreatedEvent {

    private final UUID productId;
    private final String name;
}
