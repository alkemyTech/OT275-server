package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.News;
import com.alkemy.ong.infrastructure.rest.response.GetNewsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetNewsMapper {

  private final GetCategoryMapper getCategoryMapper;

  public GetNewsResponse toResponse(News news) {
    if (news == null) {
      return null;
    }

    GetNewsResponse getNewsResponse = new GetNewsResponse();
    getNewsResponse.setId(news.getId());
    getNewsResponse.setName(news.getName());
    getNewsResponse.setImageUrl(news.getImageUrl());
    getNewsResponse.setContent(news.getContent());
    getNewsResponse.setCategory(getCategoryMapper.toResponse(news.getCategory()));

    return getNewsResponse;
  }

}
