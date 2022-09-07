package com.alkemy.ong.application.service;

import com.alkemy.ong.application.repository.INewsRepository;
import com.alkemy.ong.application.service.usecase.ICreateNewsUseCase;
import com.alkemy.ong.domain.News;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateNewsUseCaseService implements ICreateNewsUseCase {

  private final INewsRepository newsRepository;

  @Override
  public News add(News news) {
    return newsRepository.add(news);
  }

}
