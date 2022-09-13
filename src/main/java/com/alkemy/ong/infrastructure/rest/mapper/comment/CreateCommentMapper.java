package com.alkemy.ong.infrastructure.rest.mapper.comment;

import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.domain.News;
import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.rest.request.comment.CreateCommentRequest;
import com.alkemy.ong.infrastructure.rest.response.CreateCommentResponse;
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

  public CreateCommentResponse toResponse(Comment comment) {
    if (comment == null) {
      return null;
    }
    CreateCommentResponse response = new CreateCommentResponse();
    response.setId(comment.getId());
    response.setBody(comment.getBody());
    response.setBody(comment.getBody());
    response.setCreatedBy(comment.getCreatedBy().getFirstName()
        + " " + comment.getCreatedBy().getLastName());
    response.setAssociatedNews(comment.getAssociatedNews().getName());
    response.setCreateTimestamp(comment.getCreateTimestamp());
    return response;
  }
}
