package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.repository.ICommentRepository;
import com.alkemy.ong.application.repository.INewsRepository;
import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.application.service.usecase.ICreateCommentUseCase;
import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.domain.News;
import com.alkemy.ong.domain.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateCommentUseCaseService implements ICreateCommentUseCase {

  private final ICommentRepository commentRepository;
  private final IUserRepository userRepository;
  private final INewsRepository newsRepository;

  @Override
  public Comment create(Comment comment) {
    User user = userRepository.findBy(() -> comment.getCreatedBy().getId());
    News news = newsRepository.get(() -> comment.getAssociatedNews().getId());
    if (user == null) {
      throw new ObjectNotFoundException(ErrorMessage.OBJECT_NOT_FOUND.getMessage("User"));
    }
    if (news == null) {
      throw new ObjectNotFoundException(ErrorMessage.OBJECT_NOT_FOUND.getMessage("News"));
    }
    comment.setCreatedBy(user);
    comment.setAssociatedNews(news);
    return commentRepository.create(comment);
  }
}