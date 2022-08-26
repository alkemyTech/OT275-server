package com.alkemy.ong.infrastructure.config.spring.exception;

import com.alkemy.ong.application.exception.ObjectNotFound;
import com.alkemy.ong.infrastructure.rest.response.ErrorResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class RestExceptionHandler {

  private static final String OBJECT_NOT_FOUND = "Object not found in database.";
  private static final String INVALID_ARGUMENT = "Invalid input data";

  @ExceptionHandler(value = ObjectNotFound.class)
  protected ResponseEntity<ErrorResponse> handleObjectNotFound(ObjectNotFound e) {
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), OBJECT_NOT_FOUND,
        e.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex) {
    List<String> errors = collectErrors(ex);

    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
        INVALID_ARGUMENT, errors);

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  private List<String> collectErrors(MethodArgumentNotValidException ex) {
    return ex.getBindingResult().getFieldErrors().stream().map(this::formatErrorField)
        .collect(Collectors.toList());
  }

  private String formatErrorField(FieldError fieldError) {
    return String.format("%s : %s", fieldError.getField(), fieldError.getDefaultMessage());
  }

}