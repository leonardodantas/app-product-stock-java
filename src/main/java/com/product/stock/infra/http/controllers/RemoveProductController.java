package com.product.stock.infra.http.controllers;

import com.product.stock.app.usecases.RemoveProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/product")
public class RemoveProductController {

    private static final Logger logger = LoggerFactory.getLogger(RemoveProductController.class);
    private final RemoveProduct removeProduct;

    public RemoveProductController(final RemoveProduct removeProduct) {
        this.removeProduct = removeProduct;
    }

    @DeleteMapping("code/{productCode}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeByCode(@PathVariable(name = "productCode") final String code) {
        removeProduct.removeByCode(code);
    }

}
