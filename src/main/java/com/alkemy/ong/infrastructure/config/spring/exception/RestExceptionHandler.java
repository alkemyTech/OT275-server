package com.alkemy.ong.infrastructure.config.spring.exception;

import com.alkemy.ong.application.exception.ObjectNotFound;
import com.alkemy.ong.infrastructure.rest.response.ErrorResponse;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class RestExceptionHandler {

  private static final String OBJECT_NOT_FOUND = "Object not found in database.";

  @ExceptionHandler(value = ObjectNotFound.class)
  protected ResponseEntity<ErrorResponse> handleObjectNotFound(ObjectNotFound e) {
    ErrorResponse errorResponse = buildError(HttpStatus.NOT_FOUND,
        OBJECT_NOT_FOUND,
        e.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  private ErrorResponse buildError(HttpStatus httpStatus, String message, List<String> moreInfo) {
    return ErrorResponse.builder()
        .statusCode(httpStatus.value())
        .message(message)
        .moreInfo(moreInfo)
        .build();
  }

  private ErrorResponse buildError(HttpStatus httpStatus, String message, String moreInfo) {
    return buildError(httpStatus, message, List.of(moreInfo));
  }

}
