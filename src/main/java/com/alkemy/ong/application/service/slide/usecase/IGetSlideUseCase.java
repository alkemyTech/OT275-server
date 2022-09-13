package com.alkemy.ong.application.service.slide.usecase;

import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.domain.Slide;

public interface IGetSlideUseCase {

  Slide getBy(Identifiable<Long> identifiable);

}
