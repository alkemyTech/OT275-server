package com.alkemy.ong.application.service.delegate;

import com.alkemy.ong.domain.Identifiable;

public interface IOperationAllowed {

  boolean isAuthorized(Identifiable<Long> identifiable);

}
