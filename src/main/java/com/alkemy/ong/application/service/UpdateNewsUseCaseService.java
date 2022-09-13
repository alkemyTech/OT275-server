package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.repository.INewsRepository;
import com.alkemy.ong.application.service.usecase.IUpdateNewsUseCase;
import com.alkemy.ong.domain.News;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateNewsUseCaseService implements IUpdateNewsUseCase {

  private final INewsRepository newsRepository;

  @Override
  public News update(News news) {
    if (!newsRepository.exists(news::getId)) {
      throw new ObjectNotFoundException(ErrorMessage.OBJECT_NOT_FOUND.getMessage("News"));
    }
    return newsRepository.update(news);
  }

}