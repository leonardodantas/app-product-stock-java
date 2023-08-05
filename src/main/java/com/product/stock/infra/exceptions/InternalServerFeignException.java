package com.product.stock.infra.exceptions;

public class InternalServerFeignException extends RuntimeException {

    private final String message;
    private final String error;
    private final int httpStatus;

    public InternalServerFeignException(final String message, final String error, final int httpStatus) {
        this.message = message;
        this.error = error;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public String getError() {
        return error;
    }
}
