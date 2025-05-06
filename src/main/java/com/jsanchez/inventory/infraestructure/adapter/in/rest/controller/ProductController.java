package com.jsanchez.inventory.infraestructure.adapter.in.rest.controller;

import com.jsanchez.inventory.application.port.in.CreateProductUseCase;
import com.jsanchez.inventory.application.port.in.GetProductUseCase;
import com.jsanchez.inventory.domain.model.Product;
import com.jsanchez.inventory.infraestructure.adapter.in.rest.dto.CreateProductRequest;
import com.jsanchez.inventory.infraestructure.adapter.in.rest.dto.ProductResponse;
import com.jsanchez.inventory.infraestructure.adapter.in.rest.mapper.ProductRestMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Validated
@RestController
@RequestMapping("/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final GetProductUseCase getProductUseCase;

    public ProductController(CreateProductUseCase createProductUseCase, GetProductUseCase getProductUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.getProductUseCase = getProductUseCase;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody CreateProductRequest request) {
        Product created = createProductUseCase.createProduct(request.name(), request.quantity());
        return ResponseEntity.ok(ProductRestMapper.toResponse(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable UUID id) {
        return getProductUseCase.getProductById(id)
                .map(product -> ResponseEntity.ok(
                        new ProductResponse(product.getId().toString(), product.getName(), product.getQuantity())))
                .orElse(ResponseEntity.notFound().build());
    }
}
