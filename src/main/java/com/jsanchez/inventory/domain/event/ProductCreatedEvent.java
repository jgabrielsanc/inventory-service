package com.jsanchez.inventory.domain.event;

import java.util.UUID;

public record ProductCreatedEvent(UUID productId, String name) {

}
