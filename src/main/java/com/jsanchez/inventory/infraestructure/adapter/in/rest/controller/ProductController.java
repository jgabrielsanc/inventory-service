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

/**
 * REST controller that exposes product-related endpoints under {@code /products}.
 * <p>
 * Acts as the entry point for HTTP requests and delegates business logic
 * to the corresponding use cases following the hexagonal architecture pattern.
 * </p>
 */
@Validated
@RestController
@RequestMapping("/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final GetProductUseCase getProductUseCase;

    /**
     * Constructs a {@code ProductController} with the required use cases.
     *
     * @param createProductUseCase use case for creating products
     * @param getProductUseCase    use case for retrieving products
     */
    public ProductController(CreateProductUseCase createProductUseCase, GetProductUseCase getProductUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.getProductUseCase = getProductUseCase;
    }

    /**
     * Creates a new product from the given request body.
     *
     * @param request the request body containing the product data to create
     * @return a {@link ResponseEntity} containing the {@link ProductResponse} of the created product
     */
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody CreateProductRequest request) {
        Product created = createProductUseCase.createProduct(ProductRestMapper.toDomain(request));
        return ResponseEntity.ok(ProductRestMapper.toResponse(created));
    }

    /**
     * Retrieves a product by its unique identifier.
     *
     * @param id the UUID of the product to retrieve
     * @return a {@link ResponseEntity} containing the {@link ProductResponse} with the product data,
     *         or an appropriate error response if the product is not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable(value = "id") UUID id) {
        Product product = getProductUseCase.getProduct(id);
        return ResponseEntity.ok(ProductRestMapper.toResponse(product));
    }
}
