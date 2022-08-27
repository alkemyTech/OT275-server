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
  private static final String INVALID_ARGUMENT = "Invalid input data.";
  private static final String ERROR_OCCURS = "Application has encountered an error.";

  @ExceptionHandler(value = Exception.class)
  protected ResponseEntity<ErrorResponse> handleGenericException(Exception e) {
    ErrorResponse errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
        ERROR_OCCURS,
        e);

    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = ObjectNotFound.class)
  protected ResponseEntity<ErrorResponse> handleObjectNotFound(ObjectNotFound e) {
    ErrorResponse errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND, OBJECT_NOT_FOUND, e);

    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(
      MethodArgumentNotValidException e) {
    ErrorResponse errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST,
        INVALID_ARGUMENT,
        collectErrors(e));

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  private List<String> collectErrors(MethodArgumentNotValidException ex) {
    return ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(this::formatErrorField)
        .collect(Collectors.toList());
  }

  private String formatErrorField(FieldError fieldError) {
    return String.format("%s : %s", fieldError.getField(), fieldError.getDefaultMessage());
  }

  private static ErrorResponse buildErrorResponse(HttpStatus httpStatus, String message,
      Exception e) {
    return new ErrorResponse(httpStatus.value(), message, e.getMessage());
  }

  private static ErrorResponse buildErrorResponse(HttpStatus httpStatus, String message,
      List<String> moreInfo) {
    return new ErrorResponse(httpStatus.value(), message, moreInfo);
  }

}