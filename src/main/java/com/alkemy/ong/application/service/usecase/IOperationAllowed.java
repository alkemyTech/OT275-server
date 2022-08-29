package com.alkemy.ong.application.service.usecase;

import com.alkemy.ong.domain.Identifiable;

public interface IOperationAllowed {

  boolean isAuthorized(Identifiable<Long> identifiable);

}
