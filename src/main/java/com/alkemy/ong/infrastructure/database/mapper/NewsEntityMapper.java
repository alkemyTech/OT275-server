package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.News;
import com.alkemy.ong.infrastructure.database.entity.NewsEntity;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;

@AllArgsConstructor
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
    news.setCreateTimestamp(entity.getCreateTimestamp().getTime());
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
    entity.setCreateTimestamp(new Timestamp(news.getCreateTimestamp()));
    return entity;
  }
}
