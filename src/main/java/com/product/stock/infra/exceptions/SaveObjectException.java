package com.product.stock.infra.exceptions;

public class SaveObjectException extends RuntimeException implements CustomException {

    private final String error;

    public SaveObjectException(final String error, final Exception exception) {
        super(exception);
        this.error = error;
    }

    @Override
    public String getError() {
        return error;
    }
}
