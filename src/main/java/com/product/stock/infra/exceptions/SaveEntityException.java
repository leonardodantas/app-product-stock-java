package com.product.stock.infra.exceptions;

public class SaveEntityException extends RuntimeException {

    public SaveEntityException(final String message, final Exception exception) {
        super(message, exception);
    }
}
