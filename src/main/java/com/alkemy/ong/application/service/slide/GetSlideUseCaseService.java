package com.alkemy.ong.application.service.slide;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.repository.ISlideRepository;
import com.alkemy.ong.application.service.slide.usecase.IGetSlideUseCase;
import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.domain.Slide;
import java.util.Optional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetSlideUseCaseService implements IGetSlideUseCase {

  private final ISlideRepository slideRepository;

  @Override
  public Slide getBy(Identifiable<Long> identifiable) {
    Optional<Slide> slide = slideRepository.getBy(identifiable);
    if (slide.isEmpty()) {
      throw new ObjectNotFoundException(ErrorMessage.OBJECT_NOT_FOUND.getMessage("Slide"));
    }
    return slide.get();
  }

}
