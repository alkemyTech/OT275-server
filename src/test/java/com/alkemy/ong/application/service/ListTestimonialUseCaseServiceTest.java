package com.alkemy.ong.application.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.alkemy.ong.application.service.testimonial.ListTestimonialUseCaseService;
import com.alkemy.ong.domain.Testimonial;
import com.alkemy.ong.infrastructure.database.repository.TestimonialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class ListTestimonialUseCaseServiceTest {

  @Mock
  private TestimonialRepository testimonialRepository;
  private ListTestimonialUseCaseService listTestimonialUseCaseService;

  @BeforeEach
  void setUp() {
    listTestimonialUseCaseService = new ListTestimonialUseCaseService(testimonialRepository);
  }

  @Test
  void shouldReturnPage() {
    Pageable pageable = Pageable.unpaged();
    Page<Testimonial> page = Page.empty();

    given(testimonialRepository.findAll(pageable)).willReturn(page);

    Page<Testimonial> actualPage = listTestimonialUseCaseService.findAll(pageable);

    assertNotNull(actualPage);
    verify(testimonialRepository, times(1)).findAll(pageable);
  }
}
