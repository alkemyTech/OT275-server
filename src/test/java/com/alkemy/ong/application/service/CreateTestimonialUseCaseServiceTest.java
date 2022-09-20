package com.alkemy.ong.application.service;

import static org.mockito.Mockito.verify;

import com.alkemy.ong.application.repository.ITestimonialRepository;
import com.alkemy.ong.application.service.testimonial.CreateTestimonialUseCaseService;
import com.alkemy.ong.builder.TestimonialBuilder;
import com.alkemy.ong.domain.Testimonial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateTestimonialUseCaseServiceTest {

  @Mock
  private ITestimonialRepository testimonialRepository;
  @Mock
  private Testimonial testimonial;

  private CreateTestimonialUseCaseService createTestimonialUseCaseService;


  @BeforeEach
  void setUp() {
    createTestimonialUseCaseService = new CreateTestimonialUseCaseService(testimonialRepository);
  }

  @Test
  void shouldAddTestimonial() {
    testimonial = TestimonialBuilder.random();

    createTestimonialUseCaseService.create(testimonial);

    verify(testimonialRepository).save(testimonial);
  }
}
