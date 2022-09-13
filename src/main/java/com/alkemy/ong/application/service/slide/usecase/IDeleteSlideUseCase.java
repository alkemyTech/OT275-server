package com.alkemy.ong.application.service.slide.usecase;

import com.alkemy.ong.domain.Identifiable;

public interface IDeleteSlideUseCase {

  void delete(Identifiable<Long> identifiable);

}
