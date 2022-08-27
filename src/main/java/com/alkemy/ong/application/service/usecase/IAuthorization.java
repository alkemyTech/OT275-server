package com.alkemy.ong.application.service.usecase;

import com.alkemy.ong.domain.Identifiable;

public interface IAuthorization {

  boolean isAuthorized(Identifiable<Long> identifiable);

}
