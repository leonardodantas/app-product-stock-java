package com.product.stock.infra.http.controllers;

import com.product.stock.app.usecases.UpdateProduct;
import com.product.stock.infra.http.converters.ProductRequestConverter;
import com.product.stock.infra.http.jsons.requests.ProductUpdateRequest;
import com.product.stock.infra.http.jsons.response.ProductResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/product")
public class UpdateProductController {

    private static final Logger logger = LoggerFactory.getLogger(UpdateProductController.class);
    private final UpdateProduct updateProduct;
    private final ProductRequestConverter converter;

    public UpdateProductController(final UpdateProduct updateProduct, final ProductRequestConverter converter) {
        this.updateProduct = updateProduct;
        this.converter = converter;
    }

    @PutMapping("/{productCode}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse update(@PathVariable final String productCode, @Valid @RequestBody final ProductUpdateRequest request) {
        logger.info("Starting update product with code {}", productCode);
        final var product = converter.toDomain(productCode).convert(request);
        final var response = updateProduct.execute(product);
        return ProductResponse.from(response);
    }
}
