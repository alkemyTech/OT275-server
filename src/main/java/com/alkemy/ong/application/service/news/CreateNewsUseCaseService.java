package com.alkemy.ong.application.service.news;

import com.alkemy.ong.application.repository.ICategoryRepository;
import com.alkemy.ong.application.repository.INewsRepository;
import com.alkemy.ong.application.service.news.usecase.ICreateNewsUseCase;
import com.alkemy.ong.domain.Category;
import com.alkemy.ong.domain.News;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateNewsUseCaseService implements ICreateNewsUseCase {

  private final INewsRepository newsRepository;

  private final ICategoryRepository categoryRepository;

  @Override
  public News add(News news) {
    news.setCategory(getNewsCategory());
    return newsRepository.add(news);
  }

  private Category getNewsCategory() {
    return categoryRepository.findByNameIgnoreCase("news");
  }

}
