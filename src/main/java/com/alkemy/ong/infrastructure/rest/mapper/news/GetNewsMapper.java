package com.alkemy.ong.infrastructure.rest.mapper.news;

import com.alkemy.ong.domain.News;
import com.alkemy.ong.infrastructure.rest.mapper.category.GetCategoryMapper;
import com.alkemy.ong.infrastructure.rest.response.news.GetNewsResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

  public List<GetNewsResponse> toResponse(List<News> news) {
    if (news == null || news.isEmpty()) {
      return Collections.emptyList();
    }
    List<GetNewsResponse> getNewsResponses = new ArrayList<>(news.size());
    for (News n : news) {
      getNewsResponses.add(toResponse(n));
    }
    return getNewsResponses;
  }

}
