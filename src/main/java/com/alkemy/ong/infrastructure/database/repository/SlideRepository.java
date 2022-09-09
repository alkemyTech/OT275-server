package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.ISlideRepository;
import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.domain.Slide;
import com.alkemy.ong.infrastructure.database.entity.SlideEntity;
import com.alkemy.ong.infrastructure.database.mapper.SlideEntityMapper;
import com.alkemy.ong.infrastructure.database.repository.abstraction.ISlideSpringRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SlideRepository implements ISlideRepository {

  private final ISlideSpringRepository slideSpringRepository;

  private final SlideEntityMapper slideEntityMapper;

  @Override
  public boolean exists(Identifiable<Long> identifiable) {
    return slideSpringRepository.existsById(identifiable.getId());
  }

  @Override
  public void delete(Identifiable<Long> identifiable) {
    slideSpringRepository.deleteById(identifiable.getId());
  }

  @Override
  public List<Slide> findAll() {
    return slideEntityMapper.toDomain(slideSpringRepository.findAll());
  }

  @Override
  public Optional<Slide> getBy(Identifiable<Long> identifiable) {
    Optional<SlideEntity> slideEntity = slideSpringRepository.findById(identifiable.getId());
    if (slideEntity.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(slideEntityMapper.toDomain(slideEntity.get()));
  }

  @Override
  public List<Slide> findAllSortedByPosition() {
    return slideEntityMapper.toDomain(slideSpringRepository.findAllByOrderByPosition());
  }

  @Override
  public Integer findMaxPosition() {
    return slideSpringRepository.maxPosition();
  }

  @Override
  public Slide add(Slide slide) {
    SlideEntity entity = slideEntityMapper.toEntity(slide);
    return slideEntityMapper.toDomain(slideSpringRepository.save(entity));
  }

  @Override
  public Slide update(Slide slide) {
    SlideEntity slideEntity = slideEntityMapper.toEntity(slide);
    return slideEntityMapper.toDomain(slideSpringRepository.save(slideEntity));
  }
}