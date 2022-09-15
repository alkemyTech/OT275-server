package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Testimonial;
import com.alkemy.ong.infrastructure.database.entity.TestimonialEntity;
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
public class TestimonialEntityMapper {

  public TestimonialEntity toEntity(Testimonial testimonial) {
    if (testimonial == null) {
      return null;
    }
    TestimonialEntity testimonialEntity = new TestimonialEntity();
    testimonialEntity.setTestimonialId(testimonial.getId());
    testimonialEntity.setName(testimonial.getName());
    testimonialEntity.setContent(testimonial.getContent());
    testimonialEntity.setImageUrl(testimonial.getImageUrl());
    testimonialEntity.setTestimonialId(testimonial.getId());
    return testimonialEntity;
  }

  public Testimonial toDomain(TestimonialEntity testimonialEntity) {
    if (testimonialEntity == null) {
      return null;
    }
    Testimonial testimonial = new Testimonial();
    testimonial.setId(testimonialEntity.getTestimonialId());
    testimonial.setName(testimonialEntity.getName());
    testimonial.setContent(testimonialEntity.getContent());
    testimonial.setImageUrl(testimonialEntity.getImageUrl());
    testimonial.setId(testimonialEntity.getTestimonialId());
    return testimonial;
  }

  public List<Testimonial> toDomain(List<TestimonialEntity> testimonialEntities) {
    if (testimonialEntities == null || testimonialEntities.isEmpty()) {
      return Collections.emptyList();

    }
    List<Testimonial> testimonials = new ArrayList<>(testimonialEntities.size());
    for (TestimonialEntity testimonialEntity : testimonialEntities) {
      testimonials.add(toDomain(testimonialEntity));
    }
    return testimonials;

  }

  public Page<Testimonial> toPageDomain(List<TestimonialEntity> content,

      int number, int size, Long totalElements) {
    return new PageImpl<>(
        toDomain(content),
        PageRequest.of(number, size), totalElements);
  }
}
