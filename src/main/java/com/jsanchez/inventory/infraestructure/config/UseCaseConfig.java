package com.jsanchez.inventory.infraestructure.config;

import com.jsanchez.inventory.application.port.in.CreateProductUseCase;
import com.jsanchez.inventory.application.port.in.GetProductUseCase;
import com.jsanchez.inventory.application.port.out.EventPublisherPort;
import com.jsanchez.inventory.application.port.out.LoadProductPort;
import com.jsanchez.inventory.application.port.out.SaveProductPort;
import com.jsanchez.inventory.application.service.CreateProductService;
import com.jsanchez.inventory.application.service.GetProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateProductUseCase createProductUseCase(SaveProductPort saveProductPort,
                                                     EventPublisherPort eventPublisherPort) {
        return new CreateProductService(saveProductPort, eventPublisherPort);
    }

    @Bean
    public GetProductUseCase getProductUseCase(LoadProductPort loadProductPort) {
        return new GetProductService(loadProductPort);
    }
}
