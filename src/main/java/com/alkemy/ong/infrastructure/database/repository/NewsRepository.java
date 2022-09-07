package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.INewsRepository;
import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.domain.News;
import com.alkemy.ong.infrastructure.database.entity.NewsEntity;
import com.alkemy.ong.infrastructure.database.mapper.NewsEntityMapper;
import com.alkemy.ong.infrastructure.database.repository.abstraction.INewsSpringRepository;
import java.util.List;
import javax.persistence.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class NewsRepository implements INewsRepository {

  private final INewsSpringRepository newsSpringRepository;
  private final NewsEntityMapper newsEntityMapper;

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
    List<Tuple> tuples= newsSpringRepository.getNewsWithComments(identifiable.getId());
    return newsEntityMapper.toDomain(tuples);
  }

}
