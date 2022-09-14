package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.ITestimonialRepository;
import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.domain.Testimonial;
import com.alkemy.ong.infrastructure.database.entity.TestimonialEntity;
import com.alkemy.ong.infrastructure.database.mapper.TestimonialEntityMapper;
import com.alkemy.ong.infrastructure.database.repository.abstraction.ITestimonialSpringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TestimonialRepository implements ITestimonialRepository {

  private final ITestimonialSpringRepository testimonialSpringRepository;

  private final TestimonialEntityMapper testimonialEntityMapper;

  @Override
  public void delete(Identifiable<Long> identifiable) {
    testimonialSpringRepository.softDelete(identifiable.getId());
  }

  @Override
  public boolean exists(Identifiable<Long> identifiable) {
    return testimonialSpringRepository.exists(identifiable.getId()).isPresent();
  }

  @Override
  public Testimonial save(Testimonial testimonial) {
    TestimonialEntity testimonialEntity = testimonialEntityMapper.toEntity(testimonial);
    testimonialEntity.setSoftDeleted(false);
    return testimonialEntityMapper.toDomain(testimonialSpringRepository.save(testimonialEntity));
  }

  @Override
  public Page<Testimonial> findAll(Pageable pageable) {
    Page<TestimonialEntity> testimonials = testimonialSpringRepository
        .findAllBySoftDeletedFalse(pageable);
    return testimonialEntityMapper.toPageDomain(
        testimonials.getContent(),
        testimonials.getNumber(),
        testimonials.getSize(),
        testimonials.getTotalElements());
  }

}
