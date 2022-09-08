package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.INewsRepository;
import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.domain.News;
import com.alkemy.ong.infrastructure.database.entity.CommentEntity;
import com.alkemy.ong.infrastructure.database.entity.NewsEntity;
import com.alkemy.ong.infrastructure.database.mapper.CommentEntityMapper;
import com.alkemy.ong.infrastructure.database.mapper.NewsEntityMapper;
import com.alkemy.ong.infrastructure.database.repository.abstraction.INewsSpringRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class NewsRepository implements INewsRepository {

  private final INewsSpringRepository newsSpringRepository;
  private final NewsEntityMapper newsEntityMapper;

  private final CommentEntityMapper commentEntityMapper;

  @Override
  public void delete(Identifiable<Long> identifiable) {
    newsSpringRepository.softDelete(identifiable.getId());
  }

  @Override
  public boolean exists(Identifiable<Long> identifiable) {
    return newsSpringRepository.exists(identifiable.getId()).isPresent();
  }

  @Override
  public News get(Identifiable<Long> identifiable) {
    NewsEntity entity = newsSpringRepository.findByNewsIdAndSoftDeletedFalse(identifiable.getId());
    return newsEntityMapper.toDomain(entity);
  }

  @Override
  public News getWithComments(Identifiable<Long> identifiable) {
    List<Tuple> tuples = newsSpringRepository.getNewsWithComments(identifiable.getId());
    return this.toDomain(tuples);
  }

  private News toDomain(List<Tuple> tuples) {
    if (tuples == null || tuples.isEmpty()) {
      return null;
    }

    News news = new News();
    news.setName(tuples.get(0).get(0, String.class));
    news.setComments(this.toDomainTuples(tuples));

    return news;
  }

  private List<Comment> toDomainTuples(List<Tuple> tuples) {
    List<Comment> comments = new ArrayList<>(tuples.size());
    for (Tuple tuple : tuples) {
      comments.add(commentEntityMapper.toDomain(tuple.get(1, CommentEntity.class)));
    }
    return comments;
  }

}
