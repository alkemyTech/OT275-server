package com.alkemy.ong.application.service.slide.usecase;

import com.alkemy.ong.domain.Slide;
import java.util.List;

public interface IListSlideUseCase {

  List<Slide> findAll();
}
