package com.alkemy.ong.application.service.slide;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.repository.ISlideRepository;
import com.alkemy.ong.application.service.slide.usecase.IDeleteSlideUseCase;
import com.alkemy.ong.domain.Identifiable;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
public class DeleteSlideUseCaseService implements IDeleteSlideUseCase {

  private final ISlideRepository slideRepository;

  @Transactional
  @Override
  public void delete(Identifiable<Long> identifiable) {
    if (!slideRepository.exists(identifiable)) {
      throw new ObjectNotFoundException(ErrorMessage.OBJECT_NOT_FOUND.getMessage("Slide"));
    }
    slideRepository.delete(identifiable);
  }
}
