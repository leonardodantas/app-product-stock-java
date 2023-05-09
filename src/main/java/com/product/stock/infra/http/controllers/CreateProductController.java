package com.product.stock.infra.http.controllers;

import com.product.stock.app.usecases.CreateProduct;
import com.product.stock.infra.http.converters.ProductRequestConverter;
import com.product.stock.infra.http.requests.ProductRequest;
import com.product.stock.infra.http.response.ProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateProductController {

    private final CreateProduct createProduct;
    private final ProductRequestConverter productRequestConverter;

    public CreateProductController(final CreateProduct createProduct, final ProductRequestConverter productRequestConverter) {
        this.createProduct = createProduct;
        this.productRequestConverter = productRequestConverter;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(final ProductRequest request) {
        final var product = productRequestConverter.convert(request);
        final var response = createProduct.execute(product);
        return ProductResponse.from(response);
    }
}
