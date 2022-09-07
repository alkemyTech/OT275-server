package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.domain.News;
import com.alkemy.ong.infrastructure.database.entity.CommentEntity;
import com.alkemy.ong.infrastructure.database.entity.NewsEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Tuple;
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

  public News toDomain(List<Tuple> tuples) {
    if (tuples == null || tuples.isEmpty()) {
      return null;
    }

    News news = new News();
    news.setName(tuples.get(0).get(0, String.class));
    news.setComments(commentEntityMapper.toDomainTuples(tuples));

    return news;
  }


}
