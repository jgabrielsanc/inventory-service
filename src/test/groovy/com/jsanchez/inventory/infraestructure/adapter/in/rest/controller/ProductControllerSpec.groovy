package com.jsanchez.inventory.infraestructure.adapter.in.rest.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.jsanchez.inventory.application.port.in.CreateProductUseCase
import com.jsanchez.inventory.application.port.in.GetProductUseCase
import com.jsanchez.inventory.domain.model.Product
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.http.MediaType
import spock.lang.Specification
import spock.lang.Subject

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

class ProductControllerSpec extends Specification {

    CreateProductUseCase createProductUseCase = Mock()
    GetProductUseCase getProductUseCase = Mock()

    @Subject
    ProductController controller = new ProductController(createProductUseCase, getProductUseCase)

    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build()
    ObjectMapper objectMapper = new ObjectMapper()

    def "createProduct returns 200 and the created product"() {
        given: "a product returned by the use case"
        def productId = UUID.randomUUID()
        def product = new Product(productId, "Laptop", 10)

        when: "POST /products is called with a valid body"
        def result = mockMvc.perform(
                post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content('{"name":"Laptop","quantity":10}')
        )

        then: "the use case is called once and returns the created product"
        1 * createProductUseCase.createProduct(_) >> product

        and: "the response is 200 OK with the product data"
        result.andExpect(status().isOk())
              .andExpect(jsonPath('$.id').value(productId.toString()))
              .andExpect(jsonPath('$.name').value("Laptop"))
              .andExpect(jsonPath('$.quantity').value(10))
    }

    def "getProduct returns 200 and the product when it exists"() {
        given: "an existing product ID"
        def productId = UUID.randomUUID()
        def product = new Product(productId, "Monitor", 5)

        when: "GET /products/{id} is called"
        def result = mockMvc.perform(get("/products/$productId"))

        then: "the use case is called once with the correct ID and returns the product"
        1 * getProductUseCase.getProduct(productId) >> product

        and: "the response is 200 OK with the product data"
        result.andExpect(status().isOk())
              .andExpect(jsonPath('$.id').value(productId.toString()))
              .andExpect(jsonPath('$.name').value("Monitor"))
              .andExpect(jsonPath('$.quantity').value(5))
    }

    def "getProduct propagates exception when the product does not exist"() {
        given: "a non-existing product ID and a use case that throws NoSuchElementException"
        def productId = UUID.randomUUID()
        getProductUseCase.getProduct(productId) >> { throw new NoSuchElementException("Product not found") }

        when: "GET /products/{id} is called"
        mockMvc.perform(get("/products/$productId"))

        then: "the exception propagates (no @ExceptionHandler defined)"
        thrown(Exception)
    }
}
