package com.product.stock.infra.http.converters;

import com.product.stock.domain.Error;
import com.product.stock.infra.exceptions.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CustomExceptionToError {

    public Converter<CustomException, Error> toError(final HttpServletRequest request, final HttpStatus status) {
        return (exception) -> Error.builder()
                .message(exception.getMessage())
                .error(exception.getError())
                .path(request.getRequestURI())
                .status(status)
                .exception(exception.getClass().getSimpleName())
                .build();
    }
}
