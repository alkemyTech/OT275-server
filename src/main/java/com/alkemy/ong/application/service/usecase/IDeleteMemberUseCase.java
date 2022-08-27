package com.alkemy.ong.application.service.usecase;

import com.alkemy.ong.domain.Identifiable;

public interface IDeleteMemberUseCase {

  void delete(Identifiable<Long> identifiable);

}
