package com.alkemy.ong.infrastructure.config.spring.exception;

import com.alkemy.ong.infrastructure.rest.response.ErrorResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestValidationExceptionHandler extends ResponseEntityExceptionHandler {

  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
      WebRequest request) {
    List<String> errors = new ArrayList<>();

    ex.getBindingResult().getFieldErrors()
        .forEach(fieldError -> errors.add(
            String.format("%s : %s", fieldError.getField(), fieldError.getDefaultMessage())));

    ex.getBindingResult().getGlobalErrors()
        .forEach(objectError -> errors.add(String.format("%s : %s", objectError.getObjectName(),
            objectError.getDefaultMessage())));

    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.toString(),
        ex.getLocalizedMessage(),
        errors);
    return handleExceptionInternal(ex, errorResponse, headers, HttpStatus.BAD_REQUEST, request);
  }
}




