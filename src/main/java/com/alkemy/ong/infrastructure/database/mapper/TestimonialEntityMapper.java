package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Testimonial;
import com.alkemy.ong.infrastructure.database.entity.TestimonialEntity;
import org.springframework.stereotype.Component;

@Component
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

}
