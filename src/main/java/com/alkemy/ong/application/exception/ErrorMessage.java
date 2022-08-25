package com.alkemy.ong.application.exception;

public enum ErrorMessage {
  OBJECT_NOT_FOUND(" not found");
  private String message;

  ErrorMessage(String message) {
    this.message = message;
  }

  public String getMessage(String object) {
    return object + message;
  }

}