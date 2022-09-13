package com.alkemy.ong.application.service.slide;

import com.alkemy.ong.application.repository.ISlideRepository;
import com.alkemy.ong.application.service.slide.usecase.IListSlideUseCase;
import com.alkemy.ong.domain.Slide;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ListSlideUseCaseService implements IListSlideUseCase {

  private final ISlideRepository slideRepository;

  @Override
  public List<Slide> findAll() {
    return slideRepository.findAll();
  }
}
