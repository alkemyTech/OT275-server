package com.alkemy.ong.infrastructure.rest.mapper.news;

import com.alkemy.ong.domain.News;
import com.alkemy.ong.infrastructure.rest.mapper.comment.GetCommentMapper;
import com.alkemy.ong.infrastructure.rest.response.GetNewsWithCommentsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetNewsWithCommentsMapper {

  private final GetCommentMapper getCommentMapper;

  public GetNewsWithCommentsResponse toResponse(News news) {
    if (news == null) {
      return null;
    }

    GetNewsWithCommentsResponse getNewsWithCommentsResponse = new GetNewsWithCommentsResponse();
    getNewsWithCommentsResponse.setName(news.getName());
    getNewsWithCommentsResponse.setComments(getCommentMapper.toResponse(news.getComments()));
    return getNewsWithCommentsResponse;
  }

}
