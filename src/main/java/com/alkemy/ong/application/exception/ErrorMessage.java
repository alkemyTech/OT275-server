package com.alkemy.ong.application.exception;

import java.text.MessageFormat;

public enum ErrorMessage {
  OBJECT_NOT_FOUND("{0} not found");
  private String message;

  ErrorMessage(String message) {
    this.message = message;
  }

  public String getMessage(String object) {
    return MessageFormat.format(message,object);
  }

}