package com.alkemy.ong.infrastructure.rest.mapper.comment;

import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.domain.News;
import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.rest.request.comment.CreateCommentRequest;
import com.alkemy.ong.infrastructure.rest.response.comment.FullCommentResponse;
import java.text.MessageFormat;
import org.springframework.stereotype.Component;

@Component
public class CreateCommentMapper {

  public Comment toDomain(CreateCommentRequest request) {
    if (request == null) {
      return null;
    }
    Comment comment = new Comment();
    User user = new User();
    user.setId(request.getUserId());
    News news = new News();
    news.setId(request.getNewsId());
    comment.setBody(request.getBody());
    comment.setCreatedBy(user);
    comment.setAssociatedNews(news);
    return comment;
  }

  public FullCommentResponse toResponse(Comment comment) {
    if (comment == null) {
      return null;
    }
    FullCommentResponse response = new FullCommentResponse();
    response.setId(comment.getId());
    response.setBody(comment.getBody());
    response.setBody(comment.getBody());
    response.setCreatedBy(getCreatedBy(comment.getCreatedBy()));
    response.setAssociatedNews(comment.getAssociatedNews().getName());
    response.setCreateTimestamp(comment.getCreateTimestamp());
    return response;
  }

  private String getCreatedBy(User user) {
    return MessageFormat.format("{0} {1}", user.getFirstName(), user.getLastName());
  }
}
