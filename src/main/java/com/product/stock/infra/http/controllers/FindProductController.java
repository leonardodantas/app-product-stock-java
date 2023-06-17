package com.product.stock.infra.http.controllers;

import com.product.stock.app.usecases.FindProduct;
import com.product.stock.infra.http.jsons.response.ProductResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/product")
public class FindProductController {

    private static final Logger logger = LoggerFactory.getLogger(FindProductController.class);
    private final FindProduct findProduct;

    public FindProductController(final FindProduct findProduct) {
        this.findProduct = findProduct;
    }

    @GetMapping("code/{productCode}")
    public ResponseEntity<?> findByCode(@PathVariable(name = "productCode") final String code) {
        logger.info("Starting find product by code {}", code);
        return findProduct.findByCode(code)
                .map(product -> ResponseEntity.ok(ProductResponse.from(product)))
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("id/{productId}")
    public ResponseEntity<?> findById(@PathVariable(name = "productId") final String id) {
        logger.info("Starting find product by id {}", id);
        return findProduct.findById(id)
                .map(product -> ResponseEntity.ok(ProductResponse.from(product)))
                .orElse(ResponseEntity.noContent().build());
    }
}
