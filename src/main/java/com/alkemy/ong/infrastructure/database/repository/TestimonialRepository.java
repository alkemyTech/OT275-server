package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.ITestimonialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TestimonialRepository implements ITestimonialRepository {

  private final ITestimonialSpringRepository testimonialSpringRepository;

}
