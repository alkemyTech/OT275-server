package com.alkemy.ong.application.exception;

import java.text.MessageFormat;

public enum ErrorMessage {
  OBJECT_NOT_FOUND("{0} not found."),
  INVALID_CREDENTIALS("Incorrect Username or Password.");

  private final String message;

  ErrorMessage(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public String getMessage(String object) {
    return MessageFormat.format(message, object);
  }

}