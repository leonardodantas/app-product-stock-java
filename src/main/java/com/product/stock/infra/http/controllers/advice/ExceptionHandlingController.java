package com.product.stock.infra.http.controllers.advice;

import com.product.stock.app.exceptions.DocumentAlreadyExistException;
import com.product.stock.app.exceptions.ProductNotFoundException;
import com.product.stock.infra.exceptions.InternalServerFeignException;
import com.product.stock.infra.exceptions.ResponseFeignException;
import com.product.stock.infra.exceptions.SaveObjectException;
import com.product.stock.infra.http.jsons.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(SaveObjectException.class)
    public ResponseEntity<ErrorResponse> handlerSaveObjectException(final SaveObjectException exception, final HttpServletRequest request) {

        final var response = ErrorResponse.builder()
                .message(exception.getMessage())
                .error(exception.getError())
                .path(request.getRequestURI())
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .exception(SaveObjectException.class.getSimpleName())
                .build();

        return new ResponseEntity<>(response, response.status());
    }

    @ExceptionHandler(DocumentAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handlerDocumentAlreadyExistException(final DocumentAlreadyExistException exception, final HttpServletRequest request) {

        final var response = ErrorResponse.builder()
                .message(exception.getMessage())
                .error(exception.getLocalizedMessage())
                .path(request.getRequestURI())
                .status(HttpStatus.BAD_REQUEST)
                .exception(DocumentAlreadyExistException.class.getSimpleName())
                .build();

        return new ResponseEntity<>(response, response.status());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerProductNotFoundException(final ProductNotFoundException exception, final HttpServletRequest request) {

        final var response = ErrorResponse.builder()
                .message(exception.getMessage())
                .error(exception.getLocalizedMessage())
                .path(request.getRequestURI())
                .status(HttpStatus.NOT_FOUND)
                .exception(ProductNotFoundException.class.getSimpleName())
                .build();

        return new ResponseEntity<>(response, response.status());
    }


    @ExceptionHandler(InternalServerFeignException.class)
    public ResponseEntity<ErrorResponse> handlerInternalServerFeignException(final InternalServerFeignException exception, final HttpServletRequest request) {

        final var response = ErrorResponse.builder()
                .message(exception.getMessage())
                .error(exception.getLocalizedMessage())
                .path(request.getRequestURI())
                .status(HttpStatus.valueOf(exception.getHttpStatus()))
                .exception(InternalServerFeignException.class.getSimpleName())
                .build();

        return new ResponseEntity<>(response, response.status());
    }


    @ExceptionHandler(ResponseFeignException.class)
    public ResponseEntity<ErrorResponse> handlerResponseFeignException(final ResponseFeignException exception, final HttpServletRequest request) {

        final var response = ErrorResponse.builder()
                .message(exception.getMessage())
                .error(exception.getLocalizedMessage())
                .path(request.getRequestURI())
                .status(HttpStatus.valueOf(exception.getHttpStatus()))
                .exception(ResponseFeignException.class.getSimpleName())
                .build();

        return new ResponseEntity<>(response, response.status());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerMethodArgumentNotValidException(final MethodArgumentNotValidException exception, final HttpServletRequest request) {

        final var response = ErrorResponse.builder()
                .message(exception.getMessage())
                .error(exception.getLocalizedMessage())
                .path(request.getRequestURI())
                .status(HttpStatus.BAD_REQUEST)
                .exception(MethodArgumentNotValidException.class.getSimpleName())
                .build();

        return new ResponseEntity<>(response, response.status());
    }
}
