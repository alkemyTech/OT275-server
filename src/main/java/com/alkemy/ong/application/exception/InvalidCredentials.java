package com.alkemy.ong.application.exception;

public class InvalidCredentials extends RuntimeException {

  public InvalidCredentials(String message) {
    super(message);
  }

}
