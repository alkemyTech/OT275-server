package com.alkemy.ong.application.service;

import com.alkemy.ong.application.repository.ISlideRepository;
import com.alkemy.ong.application.service.usecase.IListSlideUseCase;
import com.alkemy.ong.domain.Slide;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ListSlideUseCaseService implements IListSlideUseCase {

  private ISlideRepository slideRepository;

  @Override
  public List<Slide> findAllByOrder() {
    return slideRepository.findAllByOrderByOrder();
  }
}
