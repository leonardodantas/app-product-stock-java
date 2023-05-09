package com.product.stock.infra.http.jsons.response;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record ErrorResponse(
        String uuid,
        String message,
        String error,
        LocalDateTime timestamp,
        HttpStatus status,
        String path,
        String exception
) {
    public static class Builder {
        private String message;
        private String error;
        private HttpStatus status;
        private String path;
        private String exception;

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder error(String error) {
            this.error = error;
            return this;
        }

        public Builder status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder exception(String exception) {
            this.exception = exception;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(UUID.randomUUID().toString(), message, error, LocalDateTime.now(), status, path, exception);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}