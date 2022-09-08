package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.News;
import com.alkemy.ong.infrastructure.database.entity.NewsEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewsEntityMapper {

  private final CategoryEntityMapper categoryEntityMapper;

  private final CommentEntityMapper commentEntityMapper;

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

  public NewsEntity toEntity(News news) {
    if (news == null) {
      return null;
    }
    NewsEntity entity = new NewsEntity();
    entity.setNewsId(news.getId());
    entity.setContent(news.getContent());
    entity.setImageUrl(news.getImageUrl());
    entity.setCategory(categoryEntityMapper.toEntity(news.getCategory()));
    return entity;
  }
}
