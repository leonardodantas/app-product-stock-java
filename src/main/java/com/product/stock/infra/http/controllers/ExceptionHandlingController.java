package com.product.stock.infra.http.controllers;

import com.product.stock.app.usecases.SaveMessageError;
import com.product.stock.infra.exceptions.SaveObjectException;
import com.product.stock.infra.http.converters.CustomExceptionToError;
import com.product.stock.infra.http.jsons.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandlingController {

    private final SaveMessageError saveMessageError;
    private final CustomExceptionToError customExceptionToError;

    public ExceptionHandlingController(final SaveMessageError saveMessageError, final CustomExceptionToError customExceptionToError) {
        this.saveMessageError = saveMessageError;
        this.customExceptionToError = customExceptionToError;
    }

    @ExceptionHandler(SaveObjectException.class)
    public ResponseEntity<ErrorResponse> handleExceptionSaveEntity(final SaveObjectException exception, final HttpServletRequest request) {

        final var error = customExceptionToError.toError(request, HttpStatus.UNPROCESSABLE_ENTITY)
                .convert(exception);
        final var response = saveMessageError.execute(error);

        return new ResponseEntity<>(ErrorResponse.from(response), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
