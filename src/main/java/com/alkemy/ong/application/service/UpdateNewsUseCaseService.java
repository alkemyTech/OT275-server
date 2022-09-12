package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.repository.ICategoryRepository;
import com.alkemy.ong.application.repository.INewsRepository;
import com.alkemy.ong.application.service.usecase.IUpdateNewsUseCase;
import com.alkemy.ong.domain.Category;
import com.alkemy.ong.domain.News;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class UpdateNewsUseCaseService implements IUpdateNewsUseCase {

  private final INewsRepository newsRepository;
  private final ICategoryRepository categoryRepository;

  @Override
  public News update(News news) {
    if (!newsRepository.exists(news::getId)) {

      throw new ObjectNotFoundException(ErrorMessage.OBJECT_NOT_FOUND.getMessage("News"));
    }
    if (news.getCategory() != null) {
      Category category = categoryRepository.get(() -> news.getCategory().getId());
      if (category == null) {
        throw new ObjectNotFoundException(ErrorMessage.OBJECT_NOT_FOUND.getMessage("Category"));
      }
      news.setCategory(category);
    }
    return newsRepository.update(news);

  }

}