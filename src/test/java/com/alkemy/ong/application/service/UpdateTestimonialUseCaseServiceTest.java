package com.alkemy.ong.application.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.repository.ITestimonialRepository;
import com.alkemy.ong.application.service.testimonial.UpdateTestimonialUseCaseService;
import com.alkemy.ong.domain.Testimonial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateTestimonialUseCaseServiceTest {

  private UpdateTestimonialUseCaseService updateTestimonialUseCaseService;

  @Mock
  private ITestimonialRepository testimonialRepository;

  @Mock
  private Testimonial testimonial;

  @BeforeEach
  void setUp() {
    updateTestimonialUseCaseService = new UpdateTestimonialUseCaseService(testimonialRepository);
  }

  @Test
  void shouldThrowExceptionWhenTestimonialDoesNotExist() {
    given(testimonialRepository.exists(any())).willReturn(false);

    assertThrows(ObjectNotFoundException.class,
        () -> updateTestimonialUseCaseService.update(testimonial));
  }

  @Test
  void shouldUpdateTestimonial() {
    given(testimonialRepository.exists(any())).willReturn(true);
    given(testimonialRepository.update(testimonial)).willReturn(testimonial);

    updateTestimonialUseCaseService.update(testimonial);

    verify(testimonialRepository).update(testimonial);
  }

}
