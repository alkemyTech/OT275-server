package com.alkemy.ong.application.exception;

public class OperationNotPermittedException extends RuntimeException {

  public OperationNotPermittedException(String message) {
    super(message);
  }

}
