package com.product.stock.infra.http.controllers;

import com.product.stock.app.usecases.FindAllProducts;
import com.product.stock.infra.http.jsons.response.PaginatedResult;
import com.product.stock.infra.http.jsons.response.ProductResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/products")
public class FindAllProductsController {

    private static final Logger logger = LoggerFactory.getLogger(FindAllProductsController.class);
    private final FindAllProducts findAllProducts;

    public FindAllProductsController(final FindAllProducts findAllProducts) {
        this.findAllProducts = findAllProducts;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PaginatedResult<ProductResponse> findAll(@RequestParam final int page, @RequestParam final int size) {
        logger.info("Starting find all products page {} and size {}", page, size);
        final var response = findAllProducts.execute(page, size);
        return PaginatedResult.from(response.map(ProductResponse::from));
    }
}
