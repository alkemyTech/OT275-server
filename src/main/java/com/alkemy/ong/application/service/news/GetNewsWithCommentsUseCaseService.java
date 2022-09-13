package com.alkemy.ong.application.service.news;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.repository.INewsRepository;
import com.alkemy.ong.application.service.news.usecase.IGetNewsWithCommentsUseCase;
import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.domain.News;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetNewsWithCommentsUseCaseService implements IGetNewsWithCommentsUseCase {

  private final INewsRepository newsRepository;

  @Override
  public News get(Identifiable<Long> identifiable) {
    News news = newsRepository.getWithComments(identifiable);
    if (news == null) {
      throw new ObjectNotFoundException(ErrorMessage.OBJECT_NOT_FOUND.getMessage("News"));
    }
    return news;
  }
}
