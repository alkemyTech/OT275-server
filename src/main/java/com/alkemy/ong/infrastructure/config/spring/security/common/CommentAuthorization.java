package com.alkemy.ong.infrastructure.config.spring.security.common;

import com.alkemy.ong.application.service.usecase.IAuthorization;
import com.alkemy.ong.domain.Identifiable;

public class CommentAuthorization implements IAuthorization {

  @Override
  public boolean isAuthorized(Identifiable<Long> identifiable) {
    return false;
  }

}