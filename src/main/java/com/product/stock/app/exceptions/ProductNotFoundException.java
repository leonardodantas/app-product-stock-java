package com.product.stock.app.exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(final String message) {
        super(message);
    }
}
