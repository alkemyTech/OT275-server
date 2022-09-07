package com.alkemy.ong.application.service;

import com.alkemy.ong.application.repository.INewsRepository;
import com.alkemy.ong.application.service.usecase.IGetNewsWithCommentsUseCase;
import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.domain.News;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetNewsWithCommentsUseCaseService implements IGetNewsWithCommentsUseCase {

  private final INewsRepository newsRepository;

  @Override
  public News get(Identifiable<Long> identifiable) {
    return newsRepository.getWithComments(identifiable);
  }
}
