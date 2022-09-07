package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.INewsRepository;
import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.domain.News;
import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.database.mapper.NewsEntityMapper;
import com.alkemy.ong.infrastructure.database.repository.abstraction.INewsSpringRepository;
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
  public News findBy(Identifiable<Long> identifiable) {
    return newsEntityMapper.toDomain(
        newsSpringRepository.findByNewsIdAndSoftDeletedFalse(identifiable.getId()));
  }
}
