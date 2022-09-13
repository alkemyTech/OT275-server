package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.domain.News;
import com.alkemy.ong.infrastructure.rest.request.UpdateNewsRequest;
import com.alkemy.ong.infrastructure.rest.response.UpdateNewsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateNewsMapper {

  private final GetCategoryMapper getCategoryMapper;

  public News toDomain(Identifiable<Long> identifiable, UpdateNewsRequest updateNewsRequest) {
    if (updateNewsRequest == null) {
      return null;
    }
    News news = new News();
    news.setId(identifiable.getId());
    news.setName(updateNewsRequest.getName());
    news.setContent(updateNewsRequest.getContent());
    news.setImageUrl(updateNewsRequest.getImageUrl());
    return news;
  }

  public UpdateNewsResponse toResponse(News news) {
    if (news == null) {
      return null;
    }
    UpdateNewsResponse updateNewsResponse = new UpdateNewsResponse();
    updateNewsResponse.setId(news.getId());
    updateNewsResponse.setName(news.getName());
    updateNewsResponse.setContent(news.getContent());
    updateNewsResponse.setImageUrl(news.getImageUrl());
    updateNewsResponse.setCategory(getCategoryMapper.toResponse(news.getCategory()));
    return updateNewsResponse;

  }

}
