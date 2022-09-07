package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.repository.ICommentRepository;
import com.alkemy.ong.application.repository.INewsRepository;
import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.application.service.usecase.ICreateCommentUseCase;
import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.domain.Identifiable;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateCommentUseCaseService implements ICreateCommentUseCase {

  private final ICommentRepository commentRepository;
  private final IUserRepository userRepository;
  private final INewsRepository newsRepository;

  @Override
  public Comment create(Comment comment, Identifiable<Long> userId, Identifiable<Long> newsId) {
    if (!userRepository.exists(userId)) {
      throw new ObjectNotFoundException(ErrorMessage.OBJECT_NOT_FOUND.getMessage("User"));
    }
    if (!newsRepository.exists(newsId)) {
      throw new ObjectNotFoundException(ErrorMessage.OBJECT_NOT_FOUND.getMessage("News"));
    }
    comment.setCreatedBy(userRepository.findBy(userId));
    comment.setAssociatedNews(newsRepository.findBy(newsId));
    return commentRepository.create(comment);
  }
}
