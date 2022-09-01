package com.alkemy.ong.infrastructure.config.spring.exception;

import com.alkemy.ong.application.exception.InvalidCredentialsException;
import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.exception.OperationNotPermittedException;
import com.alkemy.ong.application.exception.UserAlreadyExistsException;
import com.alkemy.ong.infrastructure.rest.response.ErrorResponse;
import java.util.Collections;
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
  private static final String OPERATION_NOT_PERMITTED = "Operation not permitted.";
  private static final String INVALID_CREDENTIALS = "Invalid credentials.";

  @ExceptionHandler(value = Exception.class)
  protected ResponseEntity<ErrorResponse> handleGenericException(Exception e) {
    ErrorResponse errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
        ERROR_OCCURS,
        e);

    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = ObjectNotFoundException.class)
  protected ResponseEntity<ErrorResponse> handleObjectNotFound(ObjectNotFoundException e) {
    ErrorResponse errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND, OBJECT_NOT_FOUND, e);

    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = InvalidCredentialsException.class)
  protected ResponseEntity<ErrorResponse> handleInvalidCredentials(InvalidCredentialsException e) {
    ErrorResponse errorResponse = buildErrorResponse(HttpStatus.FORBIDDEN,
        INVALID_CREDENTIALS,
        e);

    return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(value = OperationNotPermittedException.class)
  protected ResponseEntity<ErrorResponse> handleOperationNotPermitted(
      OperationNotPermittedException e) {
    ErrorResponse errorResponse = buildErrorResponse(HttpStatus.FORBIDDEN,
        OPERATION_NOT_PERMITTED,
        e);

    return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
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
    return fieldError.getDefaultMessage();
  }

  @ExceptionHandler(value = UserAlreadyExistsException.class)
  protected ResponseEntity<ErrorResponse> handleUserAlreadyExists(UserAlreadyExistsException e) {
    ErrorResponse errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST,
        INVALID_ARGUMENT,
        Collections.singletonList(e.getMessage()));
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
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