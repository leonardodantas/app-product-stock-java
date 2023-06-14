package com.product.stock.app.exceptions;

public class DocumentAlreadyExistException extends RuntimeException {
    public DocumentAlreadyExistException(final String message) {
        super(message);
    }
}
