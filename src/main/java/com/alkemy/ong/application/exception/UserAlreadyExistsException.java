package com.alkemy.ong.application.exception;

public class UserAlreadyExistsException extends RuntimeException {

  public UserAlreadyExistsException(String message) {
    super(message);
  }

}
