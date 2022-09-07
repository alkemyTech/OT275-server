package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.News;
import com.alkemy.ong.infrastructure.rest.request.CreateNewsRequest;
import com.alkemy.ong.infrastructure.rest.response.NewsResponse;
import org.springframework.stereotype.Component;

@Component
public class CreateNewsMapper {

  public News toDomain(CreateNewsRequest newsRequest) {
    if (newsRequest == null) {
      return null;
    }
    News news = new News();
    news.setName(newsRequest.getName());
    news.setContent(newsRequest.getText());
    news.setImageUrl(newsRequest.getImageUrl());
    return news;
  }

  public NewsResponse toResponse(News news) {
    if (news == null) {
      return null;
    }
    NewsResponse newsResponse = new NewsResponse();
    newsResponse.setId(news.getId());
    newsResponse.setName(news.getName());
    newsResponse.setText(news.getContent());
    newsResponse.setImageUrl(news.getImageUrl());
    return newsResponse;
  }

}
