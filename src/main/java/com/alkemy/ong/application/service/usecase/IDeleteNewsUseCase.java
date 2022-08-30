package com.alkemy.ong.application.service.usecase;

import com.alkemy.ong.domain.Identifiable;

public interface IDeleteNewsUseCase {

  void delete(Identifiable<Long> identifiable);

}
