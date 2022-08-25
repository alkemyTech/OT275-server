package com.alkemy.ong.application.service.usecase;

import com.alkemy.ong.domain.Identifiable;

public interface IDeleteUserUseCase {

  void delete(Identifiable<Long> identifiable);

}