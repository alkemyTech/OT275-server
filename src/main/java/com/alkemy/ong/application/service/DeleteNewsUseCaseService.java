package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.ObjectNotFound;
import com.alkemy.ong.application.repository.INewsRepository;
import com.alkemy.ong.application.service.usecase.IDeleteNewsUseCase;
import com.alkemy.ong.domain.Identifiable;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
public class DeleteNewsUseCaseService implements IDeleteNewsUseCase {

  private final INewsRepository newsRepository;

  @Transactional
  @Override
  public void delete(Identifiable<Long> identifiable) {
    if (!newsRepository.exists(identifiable)) {
      throw new ObjectNotFound(ErrorMessage.OBJECT_NOT_FOUND.getMessage("News"));
    }
    newsRepository.delete(identifiable);
  }

}
