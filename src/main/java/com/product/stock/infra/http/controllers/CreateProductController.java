package com.product.stock.infra.http.controllers;

import com.product.stock.app.usecases.CreateProduct;
import com.product.stock.infra.http.converters.ProductRequestConverter;
import com.product.stock.infra.http.requests.ProductRequest;
import com.product.stock.infra.http.response.ProductResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateProductController {

    private static Logger logger = LoggerFactory.getLogger(CreateProductController.class);

    private final CreateProduct createProduct;
    private final ProductRequestConverter productRequestConverter;

    public CreateProductController(final CreateProduct createProduct, final ProductRequestConverter productRequestConverter) {
        this.createProduct = createProduct;
        this.productRequestConverter = productRequestConverter;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(final ProductRequest request) {
        logger.info("Starting create product with name {}", request.name());
        final var product = productRequestConverter.convert(request);
        final var response = createProduct.execute(product);
        return ProductResponse.from(response);
    }
}