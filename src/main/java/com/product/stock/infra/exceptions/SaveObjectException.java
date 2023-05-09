package com.product.stock.infra.exceptions;

public class SaveObjectException extends RuntimeException {

    private final String error;

    public SaveObjectException(final String error, final Exception exception) {
        super(exception);
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
