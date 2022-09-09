package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.repository.ISlideRepository;
import com.alkemy.ong.application.service.usecase.IUpdateSlideUseCase;
import com.alkemy.ong.domain.Slide;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateSlideUseCaseService implements IUpdateSlideUseCase {

  private final ISlideRepository slideRepository;

  @Override
  public Slide update(Slide slide) {
    Optional<Slide> savedSlide = slideRepository.getBy(slide::getId);
    if (savedSlide.isEmpty()) {
      throw new ObjectNotFoundException(ErrorMessage.OBJECT_NOT_FOUND.getMessage("Slide"));
    }

    return update(slide, savedSlide.get());
  }

  private Slide update(Slide slide, Slide savedSlide) {
    savedSlide.setText(slide.getText());
    savedSlide.setOrder(slide.getOrder());
    return slideRepository.update(savedSlide);
  }
}