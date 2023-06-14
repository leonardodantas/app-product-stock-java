package com.product.stock.infra.http.controllers;

import com.product.stock.app.usecases.CreateProduct;
import com.product.stock.infra.http.converters.ProductRequestConverter;
import com.product.stock.infra.http.jsons.requests.ProductCreateRequest;
import com.product.stock.infra.http.jsons.response.ProductResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/product")
public class CreateProductController {

    private static final Logger logger = LoggerFactory.getLogger(CreateProductController.class);
    private final CreateProduct createProduct;
    private final ProductRequestConverter productRequestConverter;

    public CreateProductController(final CreateProduct createProduct, final ProductRequestConverter productRequestConverter) {
        this.createProduct = createProduct;
        this.productRequestConverter = productRequestConverter;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(@Valid @RequestBody final ProductCreateRequest request) {
        logger.info("Starting create product with name {}", request.name());
        final var product = productRequestConverter.convert(request);
        final var response = createProduct.execute(product);
        return ProductResponse.from(response);
    }
}
