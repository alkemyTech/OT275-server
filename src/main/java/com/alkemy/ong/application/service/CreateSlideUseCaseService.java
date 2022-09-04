package com.alkemy.ong.application.service;

import com.alkemy.ong.application.repository.ISlideRepository;
import com.alkemy.ong.application.service.usecase.ICreateSlideUseCase;
import com.alkemy.ong.application.util.IImageUploader;
import com.alkemy.ong.domain.Slide;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateSlideUseCaseService implements ICreateSlideUseCase {

  private final ISlideRepository slideRepository;

  private final IImageUploader imageUploader;

  @Override
  public Slide add(Slide slide) {
    slide.setImageUrl(imageUploader.upload(slide));

    if (slide.getOrder() == null) {
      slide.setOrder(nextPosition());
    }

    return slideRepository.add(slide);
  }

  private Integer nextPosition() {
    return slideRepository.findMaxPosition() + 1;
  }
}
