package com.alkemy.ong.application.exception;


public class CategoryAlreadyExists extends RuntimeException {

  public CategoryAlreadyExists(String message) {
    super(message);
  }
}
