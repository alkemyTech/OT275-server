package com.alkemy.ong.application.exception;

import java.text.MessageFormat;

public enum ErrorMessage {
  OBJECT_NOT_FOUND("{0} not found."),
  OPERATION_NOT_PERMITTED("Operation not permitted."),
  USER_ALREADY_EXISTS("Email is being used, try another!"),
  SERVICE_MAIL_FAILURE("Something goes wrong when sending email.");

  private final String message;

  ErrorMessage(String message) {
    this.message = message;
  }

  public String getMessage(String object) {
    return MessageFormat.format(message, object);
  }

  public String getMessage() {
    return message;
  }
}