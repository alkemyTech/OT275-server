package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.repository.INewsRepository;
import com.alkemy.ong.application.service.usecase.IGetNewsUseCase;
import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.domain.News;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetNewsUseCaseService implements IGetNewsUseCase {

  private final INewsRepository newsRepository;

  @Override
  public News get(Identifiable<Long> identifiable) {
    if (!newsRepository.exists(identifiable)) {
      throw new ObjectNotFoundException(ErrorMessage.OBJECT_NOT_FOUND.getMessage("News"));
    }

    return newsRepository.get(identifiable);
  }
}
