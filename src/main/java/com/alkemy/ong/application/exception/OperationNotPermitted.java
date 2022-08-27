package com.alkemy.ong.application.exception;

public class OperationNotPermitted extends RuntimeException {

  public OperationNotPermitted(String message) {
    super(message);
  }

}
