package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.News;
import com.alkemy.ong.infrastructure.database.entity.NewsEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewsEntityMapper {

  private final CategoryEntityMapper categoryEntityMapper;

  public News toDomain(NewsEntity entity) {
    if (entity == null) {
      return null;
    }

    News news = new News();
    news.setId(entity.getNewsId());
    news.setName(entity.getName());
    news.setContent(entity.getContent());
    news.setImageUrl(entity.getImageUrl());
    news.setCategory(categoryEntityMapper.toDomain(entity.getCategory()));

    return news;
  }

  public List<News> toDomain(List<NewsEntity> newsEntities) {
    if (newsEntities == null || newsEntities.isEmpty()) {
      return Collections.emptyList();
    }
    List<News> news = new ArrayList<>(newsEntities.size());
    for (NewsEntity newsEntity : newsEntities) {
      news.add(toDomain(newsEntity));
    }
    return news;
  }

  public NewsEntity toEntity(News news) {
    if (news == null) {
      return null;
    }
    NewsEntity entity = new NewsEntity();
    entity.setNewsId(news.getId());
    entity.setName(news.getName());
    entity.setContent(news.getContent());
    entity.setImageUrl(news.getImageUrl());
    entity.setCategory(categoryEntityMapper.toEntity(news.getCategory()));
    return entity;
  }

  public Page<News> toPageDomain(List<NewsEntity> content, int number, int size,
      long totalElements) {
    return new PageImpl<>(
        toDomain(content),
        PageRequest.of(number, size),
        totalElements);
  }
}
