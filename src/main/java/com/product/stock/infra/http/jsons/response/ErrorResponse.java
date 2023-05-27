package com.product.stock.infra.http.jsons.response;

import com.product.stock.domain.Error;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorResponse(
        String uuid,
        String error,
        LocalDateTime timestamp,
        HttpStatus status,
        String path
) {
    public static ErrorResponse from(final Error error) {
        return new ErrorResponse(error.uuid(), error.error(), error.timestamp(), error.status(), error.path());
    }
}
