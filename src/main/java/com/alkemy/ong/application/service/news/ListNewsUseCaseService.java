package com.alkemy.ong.application.service.news;

import com.alkemy.ong.application.repository.INewsRepository;
import com.alkemy.ong.application.service.news.usecase.IListNewsUseCase;
import com.alkemy.ong.domain.News;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@AllArgsConstructor
public class ListNewsUseCaseService implements IListNewsUseCase {

  private final INewsRepository newsRepository;

  @Override
  public Page<News> findAll(Pageable pageable) {
    return newsRepository.findAll(pageable);
  }
}
