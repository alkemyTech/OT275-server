package com.alkemy.ong.infrastructure.config.spring.exception;

import com.alkemy.ong.application.exception.ObjectNotFound;
import com.alkemy.ong.infrastructure.rest.response.ErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler {

  private static final String OBJECT_NOT_FOUND = "Object not found in database.";

  @ExceptionHandler(value = ObjectNotFound.class)
  protected ResponseEntity<ErrorResponse> handleObjectNotFound(ObjectNotFound e) {
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.toString(),
        OBJECT_NOT_FOUND,
        e.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }
}